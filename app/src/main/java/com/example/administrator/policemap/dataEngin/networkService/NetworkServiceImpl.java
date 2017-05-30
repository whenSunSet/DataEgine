package com.example.administrator.policemap.dataEngin.networkService;


import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToNetworkRequest;
import com.example.administrator.policemap.dataEngin.transformData.TransformDataMethodDispatch;
import com.example.administrator.policemap.httpEntity.LoginEntity;

import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;


/**
 * Created by Administrator on 2017/5/16 0016.
 */
@ThreadSafe
public class NetworkServiceImpl implements Service,ToNetworkRequest{
    private boolean enable;

    public NetworkServiceImpl(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Object in(Request request, Object in) throws Exception {
        return null;
    }

    @Override
    public Object out(Request request) throws Exception {
        return TransformDataMethodDispatch.dispatch(this,null,request,null);
    }

    @Override
    public Object patrolAccountAction_login(Request request, Object in) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoginEntity.Response response=new LoginEntity.Response(new LoginEntity.UserEntity(1,"13030512957","1",(byte) 1,"男"),"杭州",1,4,"");
        return response;
    }

    @Override
    public Object initAppData(Request request, Object in) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
