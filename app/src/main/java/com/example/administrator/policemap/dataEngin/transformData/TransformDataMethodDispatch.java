package com.example.administrator.policemap.dataEngin.transformData;


import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestFlag;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToLocalDataRequest;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToNetworkRequest;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class TransformDataMethodDispatch {
    public static <OUT,IN> OUT dispatch(ToNetworkRequest<OUT,IN> toNetworkRequest,ToLocalDataRequest<OUT,IN> toLocalDataRequest,Request request, IN in) throws Exception {
        OUT result=null;
        RequestFlag flag=request.getRequestFlag();
        if ( flag instanceof RequestFlag.NONE){
            RequestFlag.NONE requestFlag=(RequestFlag.NONE)flag;

            switch (requestFlag){
                case patrolAccountAction_login:
                    result= toNetworkRequest.patrolAccountAction_login(request,in);
                    break;
            }
        }else if (flag instanceof RequestFlag.GET){
            RequestFlag.GET requestFlag=(RequestFlag.GET)flag;

            switch (requestFlag){
                case getAppConfig:
                    result= toLocalDataRequest.getAppConfig(request,in);
                    break;
                case getUserConfig:
                    result= toLocalDataRequest.getUserConfig(request,in);
                    break;
                case initAppData:
                    result= toNetworkRequest.initAppData(request,in);
                    break;

            }
        }else if (flag instanceof RequestFlag.INSERT){
            RequestFlag.INSERT requestFlag=(RequestFlag.INSERT)flag;

            switch (requestFlag){
                case updateAndGetAppConfig:
                    result= toLocalDataRequest.updateAndGetAppConfig(request,in);
                    break;
                case updateAndGetUserConfig:
                    result= toLocalDataRequest.updateAndGetUserConfig(request,in);
                    break;
            }
        }

        return result;
    }
}
