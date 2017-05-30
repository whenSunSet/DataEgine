package com.example.administrator.policemap.dataEngin;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.widget.Toast;

import com.example.administrator.policemap.dataEngin.interception.Interceptor;
import com.example.administrator.policemap.dataEngin.interception.NewThreadInterceptor;
import com.example.administrator.policemap.dataEngin.interception.RealInterceptorChain;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.localDataService.LocalDataInterceptor;
import com.example.administrator.policemap.dataEngin.localDataService.LocalDataServiceImpl;
import com.example.administrator.policemap.dataEngin.memoryCacheService.MemoryCacheInterceptor;
import com.example.administrator.policemap.dataEngin.memoryCacheService.MemoryCacheServiceImpl;
import com.example.administrator.policemap.dataEngin.networkService.NetworkInterceptor;
import com.example.administrator.policemap.dataEngin.networkService.NetworkServiceImpl;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.logging.FLog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class DataEngine {
    public static String TAG="DataEngine";
    public static Context nowContext;
    public static ObservableBoolean nowIsShowProgressBar;

    public static final List<Interceptor> mInterceptors=new ArrayList<>();
    public static final DataEngine sInstance;
    static  {
        synchronized (DataEngine.class){
            sInstance=new DataEngine();
        }
    }

    private final Service memoryCacheService=new MemoryCacheServiceImpl(true);
    private final Service mLocalDataService =new LocalDataServiceImpl(true) ;
    private final Service networkService=new NetworkServiceImpl(true);
    private DataEngine() {
        mInterceptors.add(new MemoryCacheInterceptor(memoryCacheService));
        mInterceptors.add(new NewThreadInterceptor());
        mInterceptors.add(new LocalDataInterceptor(mLocalDataService));
        mInterceptors.add(new NetworkInterceptor(networkService));
    }

    /**
     * 所有的数据请求，都是从这个方法开始。本方法在{@link LocalDataEngine}和{@link NetworkDataEngine}中使用。
     * @param request 传入的数据请求
     * @return
     */
    public Observable<Response> request(final Request request){
        final RealInterceptorChain realInterceptorChain=RealInterceptorChain.obtain(request);
        Object response;
        try {
            response = realInterceptorChain.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            // 此时抛出的异常，那么就是请求失败了，应该是内存缓存服务有问题，由于发生了未知的错误所以不应该继续运行这个请求了。
            FLog.e(TAG,"应该是内存缓存服务有问题",e);
            return Observable.just(Response.getFailedResponse(e))
                    .compose(((RxAppCompatActivity)nowContext).<Response>bindToLifecycle())
                    .filter(new Func1<Response, Boolean>() {
                        @Override
                        public Boolean call(Response appConfigObjectObjectResponse) {
                            //此时拦截链已经调用完毕，所以可以将RealInterceptorChain回收
                            realInterceptorChain.recycle();
                            Toast.makeText(nowContext, "操作失败，发生未知错误", Toast.LENGTH_SHORT).show();
                            if (nowIsShowProgressBar!=null)nowIsShowProgressBar.set(false);
                            return appConfigObjectObjectResponse.isSuccess();
                        }
                    });
        }

        Observable<?> finalResponseObservable;
        if (response instanceof Observable){
            // 这里表示需要从其他线程获取数据数据，一般是本地储存或者网络上的数据
            finalResponseObservable = (Observable<?>) response;
        } else {
            // 这里表示直接从本线程获取数据，一般是内存中的数据
            finalResponseObservable = Observable.just(response);
        }
        return finalResponseObservable
                .compose(((RxAppCompatActivity)nowContext).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        //此时拦截链已经调用完毕，所以可以将RealInterceptorChain回收
                        realInterceptorChain.recycle();
                        if (o instanceof Response){
                            // 这里表示在 本地储存或者网络请求或者想内存缓存存数据的时候 发生了问题，所以直接返回了请求错误的结果,
                            // 由于发生了未知的错误所以不应该继续运行这个请求了。
                            Toast.makeText(nowContext, "操作失败，发生未知错误", Toast.LENGTH_SHORT).show();
                            if (nowIsShowProgressBar!=null)nowIsShowProgressBar.set(false);
                            return ((Response) o).isSuccess();
                        }
                        return true;
                    }
                }).map(new Func1<Object, Response>() {
                    @Override
                    public Response call(Object o) {
                        FLog.v(TAG, request.getRequestFlag()+"请求成功");
                        FLog.v("                                         ","                                     ");
                        return Response.getCommonResponseOne(o);
                    }
                });
    }

}
