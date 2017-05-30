package com.example.administrator.policemap.dataEngin.transformData;

import com.example.administrator.policemap.dataEngin.DataEngine;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Response;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToLocalDataRequest;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToNetworkRequest;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class Transform<Response1,Response2 ,Response3> implements ToLocalDataRequest<Observable<Response<Response1,Response2,Response3>>,Observable<Response>>,
        ToNetworkRequest<Observable<Response<Response1,Response2,Response3>>,Observable<Response>> {

    public static <Response1> Observable<Response<Response1,Object,Object>> transformOne(Request request) {
        Transform<Response1,Object,Object> transform=new Transform<>();
        try {
            return TransformDataMethodDispatch.dispatch(transform,transform,request, DataEngine.sInstance.request(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.just(Response.<Response1,Object,Object>getFailedResponse(e));
        }
    }

    public static <Response1,Response2> Observable<Response<Response1,Response2,Object>> transformTwo(Request request) {
        Transform<Response1,Response2,Object> transform=new Transform<>();
        try {
            return TransformDataMethodDispatch.dispatch(transform,transform,request, DataEngine.sInstance.request(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.just(Response.<Response1,Response2,Object>getFailedResponse(e));
        }
    }
    public static <Response1,Response2,Response3> Observable<Response<Response1,Response2,Response3>> transformThree(Request request) {
        Transform<Response1,Response2,Response3> transform=new Transform<>();
        try {
            return TransformDataMethodDispatch.dispatch(transform,transform,request, DataEngine.sInstance.request(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.just(Response.<Response1,Response2,Response3>getFailedResponse(e));
        }
    }


    @Override
    public Observable<Response<Response1, Response2, Response3>> updateAndGetAppConfig(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }

    @Override
    public Observable<Response<Response1, Response2, Response3>> getAppConfig(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }

    @Override
    public Observable<Response<Response1, Response2, Response3>> updateAndGetUserConfig(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }

    @Override
    public Observable<Response<Response1, Response2, Response3>> getUserConfig(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }

    @Override
    public Observable<Response<Response1, Response2, Response3>> patrolAccountAction_login(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }

    @Override
    public Observable<Response<Response1, Response2, Response3>> initAppData(Request request, Observable<Response> responseObservable) throws Exception {
        return responseObservable
                .map(new Func1<Response, Response<Response1, Response2, Response3>>() {
                    @Override
                    public Response<Response1, Response2, Response3> call(Response response) {
                        return response;
                    }
                });
    }
}
