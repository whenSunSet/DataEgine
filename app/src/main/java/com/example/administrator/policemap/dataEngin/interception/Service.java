package com.example.administrator.policemap.dataEngin.interception;

import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;

/**
 * Created by heshixiyang on 2017/5/21.
 */
public interface Service {
    boolean isEnabled();

    void setEnable(boolean enable);

    Object in(Request request, Object in) throws Exception;

    Object out(Request request) throws Exception;
}
