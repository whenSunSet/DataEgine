package com.example.administrator.policemap.dataEngin.interception;

import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.logging.FLog;

import javax.annotation.concurrent.Immutable;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
@Immutable
public class NewThreadInterceptor implements Interceptor {
    public static String TAG="NewThreadInterceptor";

    @Override
    public Observable<Object> intercept(final Chain chain, boolean enable)  {
        return Observable.just(chain)
                .observeOn(Schedulers.io())
                .map(new Func1<Chain, Object>() {
                    @Override
                    public Object call(Chain chain) {
                        try {
                            return chain.proceed();
                        } catch (Exception e) {
                            //此时是本地存储或者网络请求出了问题
                            FLog.e(TAG,"本地存储或者网络请求出了问题",e);
                            return Response.getFailedResponse(e);
                        }
                    }
                });
    }

    @Override
    public Service getService() {
        return null;
    }
}
