package com.example.administrator.policemap.dataEngin.interception;

import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public interface Interceptor {
    Object intercept(Chain chain,boolean enable) throws Exception;

    Service getService();
    interface Chain {
        Object proceed() throws Exception;

        Request getRequest();
    }
}
