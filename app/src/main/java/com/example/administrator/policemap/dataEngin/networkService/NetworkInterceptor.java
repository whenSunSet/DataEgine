package com.example.administrator.policemap.dataEngin.networkService;

import com.example.administrator.policemap.dataEngin.interception.Interceptor;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.logging.FLog;

import javax.annotation.concurrent.Immutable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Immutable
public class NetworkInterceptor implements Interceptor{
    public static String TAG="NetworkInterceptor";

    private final Service mNetworkService;

    public NetworkInterceptor(Service networkService) {
        this.mNetworkService = networkService;
    }

    @Override
    public Object intercept(Chain chain,boolean enable) throws Exception {
        final Request request=chain.getRequest();
        boolean isEnable=enable&&getService().isEnabled();
        FLog.v(TAG,request.getRequestFlag().toString()+"请求进入");
        //如果网络服务不可用，就返回null
        if (!isEnable)return null;
        Object response=mNetworkService.out(chain.getRequest());
        FLog.v(TAG,request.getRequestFlag().toString()+"  "+TAG+"返回");
        return response;
    }

    @Override
    public Service getService() {
        return mNetworkService;
    }
}
