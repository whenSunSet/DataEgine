package com.example.administrator.policemap.baseComponent;

import android.databinding.ObservableBoolean;

import com.example.administrator.policemap.dataEngin.DataEngine;

/**
 * Created by Administrator on 2017/5/29 0029.
 */
public class BaseViewModel {
    protected BaseActivity mBaseActivity;
    public ObservableBoolean isShowProgressBar=new ObservableBoolean(false);
    public BaseViewModel(BaseActivity baseActivity) {
        mBaseActivity=baseActivity;
        DataEngine.nowIsShowProgressBar=isShowProgressBar;
    }
}
