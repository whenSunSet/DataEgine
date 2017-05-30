package com.example.administrator.policemap;

import android.app.Application;

/**
 * Created by Administrator on 2017/5/29 0029.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        myApplication=this;
        super.onCreate();
    }
}
