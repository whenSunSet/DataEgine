package com.example.administrator.policemap.dataEngin.requestAndResponse;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public interface ToNetworkRequest<OUT,IN> {
    OUT patrolAccountAction_login(Request request, IN in)throws Exception ;
    OUT initAppData(Request request, IN in)throws Exception ;
}
