package com.example.administrator.policemap.dataEngin;

import android.support.annotation.NonNull;

import com.example.administrator.policemap.dataEngin.localDataService.database.AppConfig;
import com.example.administrator.policemap.dataEngin.localDataService.sharePreference.UserConfig;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestFlag;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.dataEngin.transformData.Transform;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class LocalDataEngine {
    public static final String TAG="LocalDataEngine";

    public static final String getAppConfig="getAppConfig";
    public static final String getUserConfig="getUserConfig";

    public static Observable<AppConfig> getAppConfig(){
        return Transform.<AppConfig>transformOne(Request.setFlag(RequestFlag.GET.getAppConfig))
                .map(new Func1<Response<AppConfig, Object, Object>, AppConfig>() {
                    @Override
                    public AppConfig call(Response<AppConfig, Object, Object> appConfigObjectObjectResponse) {
                        return appConfigObjectObjectResponse.getResponse1();
                    }
                });
    }

    public static Observable<AppConfig> updateAndGetAppConfig(@NonNull AppConfig appConfig){
        return Transform.<AppConfig>transformOne(Request.setFlagParamKey(RequestFlag.INSERT.updateAndGetAppConfig,appConfig,getAppConfig))
                .map(new Func1<Response<AppConfig, Object, Object>, AppConfig>() {
                    @Override
                    public AppConfig call(Response<AppConfig, Object, Object> appConfigObjectObjectResponse) {
                        return appConfigObjectObjectResponse.getResponse1();
                    }
                });
    }

    public static Observable<UserConfig> getUserConfig(@NonNull String username){
        String key=username+getUserConfig;
        return Transform.<UserConfig>transformOne(Request.setFlagParamKey(RequestFlag.GET.getUserConfig,username,key))
                .map(new Func1<Response<UserConfig, Object, Object>, UserConfig>() {
                    @Override
                    public UserConfig call(Response<UserConfig, Object, Object> appConfigObjectObjectResponse) {
                        return appConfigObjectObjectResponse.getResponse1();
                    }
                });
    }

    public static Observable<UserConfig> updateAndGetUserConfig(@NonNull UserConfig userConfig){
        String key=userConfig.getUserEntity().patrolMobile+getUserConfig;
        return Transform.<UserConfig>transformOne(Request.setFlagParamKey(RequestFlag.INSERT.updateAndGetUserConfig,userConfig,key))
                .map(new Func1<Response<UserConfig, Object, Object>, UserConfig>() {
                    @Override
                    public UserConfig call(Response<UserConfig, Object, Object> appConfigObjectObjectResponse) {
                        return appConfigObjectObjectResponse.getResponse1();
                    }
                });
    }
}
