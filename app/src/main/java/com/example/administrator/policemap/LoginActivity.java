package com.example.administrator.policemap;

import android.databinding.DataBindingUtil;
import android.databinding.adapters.TextViewBindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.administrator.dataegine.R;
import com.example.administrator.dataegine.databinding.ActivityLoginBinding;
import com.example.administrator.policemap.baseComponent.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    public static long exitTime = 0;
    private ActivityLoginBinding mActivityLoginBinding;
    public LoginViewModel mLoginViewModel;
    public LoginUIViewModel mLoginUIViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        mActivityLoginBinding= DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginUIViewModel=new LoginUIViewModel(this);
        mLoginViewModel=new LoginViewModel(this);
        mActivityLoginBinding.setUiViewModel(mLoginUIViewModel);
        mActivityLoginBinding.setViewModel(mLoginViewModel);
    }

    public static class LoginUIViewModel{
        private LoginActivity mLoginActivity;

        public LoginUIViewModel(LoginActivity loginActivity) {
            mLoginActivity = loginActivity;
        }

        public CompoundButton.OnCheckedChangeListener rememberPassword=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)mLoginActivity.mLoginViewModel.isAutoLogin.set(false);
                mLoginActivity.mLoginViewModel.isRememberPassword.set(isChecked);
            }
        };

        public CompoundButton.OnCheckedChangeListener autoLogin=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)mLoginActivity.mLoginViewModel.isRememberPassword.set(true);
                mLoginActivity.mLoginViewModel.isAutoLogin.set(isChecked);
            }
        };

        public View.OnClickListener loginClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLoginActivity.mLoginViewModel.isShowProgressBar.get())mLoginActivity.mLoginViewModel.login();
            }
        };

        public TextViewBindingAdapter.OnTextChanged username=new TextViewBindingAdapter.OnTextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))mLoginActivity.mLoginViewModel.setUsername(s.toString());
            }
        };

        public TextViewBindingAdapter.OnTextChanged password=new TextViewBindingAdapter.OnTextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))mLoginActivity.mLoginViewModel.setPassword(s.toString());
            }
        };


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

