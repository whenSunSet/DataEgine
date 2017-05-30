package com.example.administrator.policemap.dataEngin.localDataService;

import com.example.administrator.policemap.dataEngin.interception.Interceptor;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestFlag;
import com.example.administrator.policemap.dataEngin.requestAndResponse.RequestLevel;
import com.example.administrator.policemap.logging.FLog;

import javax.annotation.concurrent.Immutable;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Immutable
public class LocalDataInterceptor implements Interceptor {
    public static String TAG="LocalDataInterceptor";
    private final Service localDataService;

    public LocalDataInterceptor(Service localDataService) {
        this.localDataService = localDataService;
    }

    @Override
    public Object intercept(Chain chain,boolean enable) throws Exception {
        final Request request=chain.getRequest();
        Object response = null;
        boolean isEnable=enable&&getService().isEnabled();
        RequestFlag requestFlag= request.getRequestFlag();
        FLog.v(TAG,request.getRequestFlag().toString()+"请求进入");

        if (requestFlag.getRequestLevel()==RequestLevel.to_local){
            //在这种情况下，如果不开启本地数据服务，那么就直接返回null
            if (!isEnable)return null;
            switch (requestFlag.getRequestType()){
                case GET:
                    //返回本地数据中的结果
                    response= localDataService.out(request);
                    break;
                case INSERT:
                    //在更新内存缓存之后，返回更新后的结果
                    response= localDataService.in(request,null);
                    break;
                case NONE:
                    //根据传入的信息和内存缓存中的信息，经过验证操作后，验证返回结果
                    response= localDataService.in(request,null);
                    break;
            }
        }else {
            boolean isNeedToNextService=true;
            switch (requestFlag.getRequestType()){
                case GET:
                    if (isEnable) {
                        //本地存储服务可以使用，就使用他
                        response = localDataService.out(request);
                        //如果从本地储存中获取的结果不为null，那么就不需要去下一个服务取数据
                        if (response != null) isNeedToNextService = false;
                        //这里不用 break 可以直接在需要进入下一个服务的情况下，复用代码。
                    }
                case INSERT:
                    // 如果请求是GET，表示从本地获取的结果是null，所以需要进入下一个服务获取数据
                    // 如果请求是INSERT 由于这种请求肯定要经过下一个服务，所以不需要判断本服务是否可用
                    if (isNeedToNextService){
                        response= chain.proceed();
                        //如果下一个服务取来的结果不是null，并且这个数据被允许存在本地（默认是允许的，除非在下一个服务返回的时候禁止了），,就把这个结果存起来
                        if (response!=null&&request.isSaveToLocal()) localDataService.in(request,response);
                    }
                    break;
                case NONE:
                    //由于这种请求肯定要经过下一个服务，所以不需要判断本服务是否可用，也不需要将返回的结果存在本地
                    response= chain.proceed();
                    break;
            }
        }
        FLog.v(TAG,request.getRequestFlag()+"  "+TAG+"返回");
        return response;
    }

    @Override
    public Service getService() {
        return localDataService;
    }
}
