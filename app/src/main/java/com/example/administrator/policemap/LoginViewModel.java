package com.example.administrator.policemap;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.policemap.baseComponent.BaseActivity;
import com.example.administrator.policemap.baseComponent.BaseViewModel;
import com.example.administrator.policemap.dataEngin.DataEngine;
import com.example.administrator.policemap.dataEngin.LocalDataEngine;
import com.example.administrator.policemap.dataEngin.NetworkDataEngine;
import com.example.administrator.policemap.dataEngin.localDataService.database.AppConfig;
import com.example.administrator.policemap.dataEngin.localDataService.sharePreference.UserConfig;
import com.example.administrator.policemap.httpEntity.LoginEntity;
import com.example.administrator.policemap.logging.FLog;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class LoginViewModel extends BaseViewModel {
    public static final String TAG ="LoginViewModel";
    private String username="";
    private String password="";

    public ObservableBoolean isRememberPassword=new ObservableBoolean(false);
    public ObservableBoolean isAutoLogin=new ObservableBoolean(false);
    public ObservableField<String> usernameObservable=new ObservableField<>("");
    public ObservableField<String> passwordObservable=new ObservableField<>("");

    public LoginViewModel(BaseActivity baseActivity) {
        super(baseActivity);
        DataEngine.nowIsShowProgressBar=isShowProgressBar;
        init();
    }

    public void init(){
        isShowProgressBar.set(true);
        LocalDataEngine.getAppConfig()
                .flatMap(new Func1<AppConfig, Observable<AppConfig>>() {
                    @Override
                    public Observable<AppConfig> call(AppConfig appConfig) {
                        //如果为null表示第一次打开app，所以进行全局数据初始化
                        if (appConfig==null) appConfig=new AppConfig(true,"13030512957",false,false);
                        FLog.v(TAG,String.valueOf(appConfig.isFirstOpenApp()));
                        //如果不是第一次打开app，将appConfig传入下面一个环节
//                        appConfig.setAutoLogin(true);
//                        appConfig.setRememberPassword(true);
                        if (appConfig.isFirstOpenApp()){
                            return LocalDataEngine.updateAndGetAppConfig(appConfig);
                        }else {
                            return Observable.just(appConfig);
                        }
                    }
                }).flatMap(new Func1<AppConfig, Observable<AppConfig>>() {
                    @Override
                    public Observable<AppConfig> call(AppConfig appConfig) {
                        //如果是第一次打开app,那么去服务器拉去数据表
                        if (appConfig.isFirstOpenApp()){
                            return NetworkDataEngine.initAppData(appConfig);
                        }else {
                            //否则，将appConfig传入下面一个环节
                            return Observable.just(appConfig);
                        }
                    }
                }).filter(new Func1<AppConfig, Boolean>() {
                    @Override
                    public Boolean call(AppConfig appConfig) {
                        //此时如果是第一次打开app，那么数据已经全部初始化完毕，更变第一次打开app的flag
                        //同时AppConfig已经准备好，可以进行接下来的操作了。
                        if (appConfig.isFirstOpenApp())
                            appConfig.setFirstOpenApp(false);
                        return true;
                    }
                }).filter(new Func1<AppConfig, Boolean>() {
                    @Override
                    public Boolean call(AppConfig appConfig) {
                        //设置界面上的：记住密码 和 自动登录 的checkBox
                        isRememberPassword.set(appConfig.isRememberPassword());
                        isAutoLogin.set(appConfig.isAutoLogin());
                        //如果全局设置中，既不需要记住密码，也不需要自动登录，那么就不去获取默认的用户信息，直接显示登录界面。
                        if ((!appConfig.isRememberPassword())&&(!appConfig.isAutoLogin())){
                            isShowProgressBar.set(false);
                            return false;
                        }
                        return true;
                    }
                }).flatMap(new Func1<AppConfig, Observable<UserConfig>>() {
                    @Override
                    public Observable<UserConfig> call(AppConfig appConfig) {
                        //根据全局配置获取默认用户的帐号，然后去SharePreference中获取默认用户数据
                        return LocalDataEngine.getUserConfig(appConfig.getNowUserPhone());
                    }
                }).filter(new Func1<UserConfig, Boolean>() {
                    @Override
                    public Boolean call(UserConfig userConfig) {
                        // 如果默认用户数据为null，说明用户数据缓存被清空了，或者app是第一次被打开,此时app不需要自动登录。
                        if (userConfig==null){
                            //弹出toast说明自动登录失败的原因，并将登录界面显示出来
                            Toast.makeText(mBaseActivity, "用户缓存被清空，请手动登录", Toast.LENGTH_SHORT).show();
                            isShowProgressBar.set(false);
                            return false;
                        }else {
                            //数据准备就绪，进行自动登录
                            usernameObservable.set(userConfig.getRememberedPhone());
                            passwordObservable.set(userConfig.getRememberedPassword());
                            username=usernameObservable.get();
                            password=passwordObservable.get();
                            return true;
                        }
                    }
                }).subscribe(new Action1<UserConfig>() {
                    @Override
                    public void call(UserConfig userConfig) {
                        login();
                    }
                });
    }

    public void login() {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mBaseActivity, "请输入完整的信息", Toast.LENGTH_SHORT).show();
            return;
        }

        isShowProgressBar.set(true);
        NetworkDataEngine.login(username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<LoginEntity.Response, Boolean>() {
                    @Override
                    public Boolean call(LoginEntity.Response response) {
                        isShowProgressBar.set(false);
                        //如果登录成功了进入下一阶段
                        if (response.getResult() == 1)
                            Toast.makeText(mBaseActivity, "用户名错误", Toast.LENGTH_SHORT).show();
                        if (response.getResult() == 2)
                            Toast.makeText(mBaseActivity, "密码错误", Toast.LENGTH_SHORT).show();
                        if (response.getResult() == 3)
                            Toast.makeText(mBaseActivity, "审核未通过", Toast.LENGTH_SHORT).show();
                        if (response.getResult() == 4){
                            isShowProgressBar.set(true);
                            return true;
                        }
                        return false;
                    }
                }).flatMap(new Func1<LoginEntity.Response, Observable<UserConfig>>() {
                    @Override
                    public Observable<UserConfig> call(LoginEntity.Response response) {
                        //对全局配置进行更新，这个更新在其他线程不会影响登录。
                        LocalDataEngine.updateAndGetAppConfig(new AppConfig(false,username,isRememberPassword.get(),isAutoLogin.get()))
                                .subscribe(new Action1<AppConfig>() {
                                    @Override
                                    public void call(AppConfig appConfig) {
                                    }
                                });
                        //对用户配置进行更新，这个更新在其他线程，但是由于在登录后会用到，所以只有用户配置更新完毕才能登录。
                        return LocalDataEngine.updateAndGetUserConfig(new UserConfig(username,password,response.getUserEntity(),response.getUnitName(),response.getOtherTopUnitId(),response.getToken()));
                    }
                }).subscribe(new Action1<UserConfig>() {
                    @Override
                    public void call(UserConfig userConfig) {
                        isShowProgressBar.set(false);
                        Toast.makeText(mBaseActivity, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mBaseActivity, MainActivity.class);
                        mBaseActivity.startActivity(intent);
                        mBaseActivity.finish();
                    }
                });
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
