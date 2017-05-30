package com.example.administrator.policemap.dataEngin.memoryCacheService;

import com.example.administrator.policemap.dataEngin.interception.Interceptor;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestFlag;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestLevel;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.logging.FLog;

import javax.annotation.concurrent.Immutable;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Immutable
public class MemoryCacheInterceptor implements Interceptor {
    public static String TAG="MemoryCacheInterceptor";
    private final Service memoryCacheService;

    public MemoryCacheInterceptor(Service memoryCacheService) {
        this.memoryCacheService = memoryCacheService;
    }

    @Override
    public Object intercept(final Chain chain, boolean enable) throws Exception {
        final Request request=chain.getRequest();
        Object response = null;
        boolean isEnable=enable&&getService().isEnabled();
        RequestFlag requestFlag= request.getRequestFlag();
        FLog.v(TAG,request.getRequestFlag().toString()+"请求进入");

        if (requestFlag.getRequestLevel()==RequestLevel.to_memory){
            //在这种情况下，如果不开启内存缓存，那么就直接返回null
            if (!isEnable) response=null;
            switch (requestFlag.getRequestType()){
                case GET:
                    //返回内存缓存中的结果
                    response= memoryCacheService.out(request);
                    break;
                case INSERT:
                    //在更新内存缓存之后，返回更新后的结果
                    response= memoryCacheService.in(request,null);
                    break;
                case NONE:
                    //在memory下，不会有这种请求
                    response= null;
                    break;
            }
        }else {
            boolean isNeedToNextService=true;
            switch (requestFlag.getRequestType()){
                case GET:
                    if (isEnable){
                        //内存缓存服务可以使用，就使用他
                        response= memoryCacheService.out(request);
                        //如果从内存缓存中获取的结果不为null，那么就不需要去下一个服务取数据
                        if (response!=null)isNeedToNextService=false;
                    }
                    //这里不用 break 可以直接在需要进入下一个服务的情况下，复用代码。
                case INSERT:
                    // 如果请求是GET，表示从内存中获取的结果是null，所以需要进入下一个服务获取数据
                    // 如果请求是INSERT 由于这种请求肯定要经过下一个服务，所以不需要判断本服务是否可用
                    // 下一个服务开始要去本地或者网络获取数据了，所以返回的是 Observable
                    if (isNeedToNextService)
                        response= ((Observable<?>)chain.proceed())
                                .map(new Func1<Object, Object>() {
                                @Override
                                public Object call(Object o) {
                                    //如果回调后的结果不是null并且允许该数据被缓存（默认是允许的，除非在下一个服务返回的时候禁止了），就缓存这个结果
                                    if (o != null && request.isCacheToMemory())try {
                                        memoryCacheService.in(request, o);
                                    } catch (Exception e) {
                                        //此时是 内存缓存的存储出了问题
                                        FLog.e(TAG, "内存缓存的存储出了问题", e);
                                        return Response.getFailedResponse(e);
                                    }
                                    return o;
                                }
                            });
                    break;
                case NONE:
                    //这种请求直接进入下一个服务，下一个服务开始要去本地或者网络获取数据了，所以返回的是 Observable
                    response= chain.proceed();
                    break;
            }
        }
        FLog.v(TAG,request.getRequestFlag().toString()+"  "+TAG+"返回");
        return response;
    }

    @Override
    public Service getService() {
        return memoryCacheService;
    }
}
