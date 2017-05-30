package com.example.administrator.policemap.dataEngin.localDataService.database;

import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity.DBMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class AppConfig {
    private boolean isFirstOpenApp=true;
    private String nowUserPhone;
    private boolean isRememberPassword;
    private boolean isAutoLogin;

    public AppConfig(List<DBMap> mapList) {
        if (mapList==null||mapList.size()<4)return;
        if (mapList.get(0)!=null) isFirstOpenApp=Boolean.parseBoolean(mapList.get(0).getValue());
        if (mapList.get(1)!=null) nowUserPhone=mapList.get(1).getValue();
        if (mapList.get(2)!=null) isRememberPassword=Boolean.parseBoolean(mapList.get(2).getValue());
        if (mapList.get(3)!=null) isAutoLogin=Boolean.parseBoolean(mapList.get(3).getValue());
    }

    public AppConfig(String nowUserPhone, boolean isRememberPassword, boolean isAutoLogin) {
        this.nowUserPhone = nowUserPhone;
        this.isRememberPassword = isRememberPassword;
        this.isAutoLogin = isAutoLogin;
    }

    public AppConfig(boolean isFirstOpenApp, String nowUserPhone, boolean isRememberPassword, boolean isAutoLogin) {
        this.isFirstOpenApp = isFirstOpenApp;
        this.nowUserPhone = nowUserPhone;
        this.isRememberPassword = isRememberPassword;
        this.isAutoLogin = isAutoLogin;
    }

    public boolean isFirstOpenApp() {
        return isFirstOpenApp;
    }

    public void setFirstOpenApp(boolean firstOpenApp) {
        isFirstOpenApp = firstOpenApp;
    }

    public String getNowUserPhone() {
        return nowUserPhone;
    }

    public void setNowUserPhone(String nowUserPhone) {
        this.nowUserPhone = nowUserPhone;
    }

    public boolean isRememberPassword() {
        return isRememberPassword;
    }

    public void setRememberPassword(boolean rememberPassword) {
        isRememberPassword = rememberPassword;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        isAutoLogin = autoLogin;
    }

    public List<DBMap> transformToDBMap(){
        List<DBMap> mapList=new ArrayList<>();
        mapList.add(new DBMap(1,"isFirstOpenApp", String.valueOf(isFirstOpenApp)));
        mapList.add(new DBMap(2,"nowUserPhone",nowUserPhone));
        mapList.add(new DBMap(3,"isRememberPassword",String.valueOf(isRememberPassword)));
        mapList.add(new DBMap(4,"isAutoLogin",String.valueOf(isAutoLogin)));
        return mapList;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "isFirstOpenApp=" + isFirstOpenApp +
                ", nowUserPhone='" + nowUserPhone + '\'' +
                ", isRememberPassword=" + isRememberPassword +
                ", isAutoLogin=" + isAutoLogin +
                '}';
    }
}
