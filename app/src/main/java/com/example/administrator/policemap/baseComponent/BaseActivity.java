package com.example.administrator.policemap.baseComponent;

import android.os.Bundle;

import com.example.administrator.policemap.dataEngin.DataEngine;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Administrator on 2017/5/29 0029.
 */
public class BaseActivity extends RxAppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataEngine.nowContext=this;
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}
