package com.example.administrator.policemap.dataEngin.requestAndResponse;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public interface ToLocalDataRequest<OUT,IN> {
    OUT updateAndGetAppConfig(Request request, IN in)throws Exception;
    OUT getAppConfig(Request request, IN in)throws Exception ;
    OUT updateAndGetUserConfig(Request request, IN in) throws Exception;
    OUT getUserConfig(Request request, IN in)throws Exception ;
}
