package com.example.administrator.policemap.dataEngin;


import android.support.annotation.NonNull;

import com.example.administrator.policemap.dataEngin.localDataService.database.AppConfig;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestFlag;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.dataEngin.transformData.Transform;
import com.example.administrator.policemap.httpEntity.LoginEntity;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class NetworkDataEngine {
    public static Observable<LoginEntity.Response> login(@NonNull String username,@NonNull String password){
        return Transform.<LoginEntity.Response>transformOne(Request.setFlagParam(RequestFlag.NONE.patrolAccountAction_login,new LoginEntity(username,password)))
                .map(new Func1<Response<LoginEntity.Response, Object, Object>, LoginEntity.Response>() {
                    @Override
                    public LoginEntity.Response call(Response<LoginEntity.Response, Object, Object> responseObjectObjectResponse) {
                        return responseObjectObjectResponse.getResponse1();
                    }
                });
    }

    public static Observable<AppConfig> initAppData(@NonNull final AppConfig appConfig){
        return Transform.<AppConfig>transformOne(Request.setFlag(RequestFlag.GET.initAppData))
                .map(new Func1<Response<AppConfig, Object, Object>, AppConfig>() {
                    @Override
                    public AppConfig call(Response<AppConfig, Object, Object> appConfigObjectObjectResponse) {
                        return appConfig;
                    }
                });
    }
}