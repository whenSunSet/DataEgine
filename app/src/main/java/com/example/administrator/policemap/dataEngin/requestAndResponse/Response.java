package com.example.administrator.policemap.dataEngin.requestAndResponse;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class Response<Response1,Response2 ,Response3> {
    private boolean isSuccess;
    private Exception mException;

    private Response1 mResponse1;
    private Response2 mResponse2;
    private Response3 mResponse3;

    public static <T1,T2,T3> Response<T1,T2,T3>  getFailedResponse(Exception exception){
        return new Response<T1,T2,T3>(false,exception);
    }

    public static Response getCommonResponseOne(Object response1){
        return new Response<Object,Object,Object>(response1);
    }

    public static Response getCommonResponseTwo(Object response1,Object response2){
        return new Response<Object,Object,Object>(response1,response2);
    }

    public static Response getCommonResponseThree(Object response1,Object response2,Object response3){
        return new Response<Object,Object,Object>(response1,response2,response3);
    }

    public static <T> Response getResponseOne(T response1){
        return new Response<T,Object,Object>(response1);
    }

    public static <T1,T2> Response getResponseTwo(T1 response1,T2 response2){
        return new Response<T1,T2,Object>(response1);
    }

    public static <T1,T2,T3> Response getResponseOne(T1 response1,T2 response2,T3 response3){
        return new Response<T1,T2,T3>(response1,response2,response3);
    }

    private Response(boolean isSuccess, Exception exception) {
        this.isSuccess = isSuccess;
        mException = exception;
    }

    private Response(Response1 response1) {
        mResponse1 = response1;
        isSuccess=true;
    }

    private Response(Response1 response1, Response2 response2) {
        mResponse1 = response1;
        mResponse2 = response2;
        isSuccess=true;
    }

    private Response(Response1 response1, Response2 response2, Response3 response3) {
        mResponse1 = response1;
        mResponse2 = response2;
        mResponse3 = response3;
        isSuccess=true;
    }

    public Response1 getResponse1() {
        return mResponse1;
    }

    public void setResponse1(Response1 response1) {
        mResponse1 = response1;
    }

    public Response2 getResponse2() {
        return mResponse2;
    }

    public void setResponse2(Response2 response2) {
        mResponse2 = response2;
    }

    public Response3 getResponse3() {
        return mResponse3;
    }

    public void setResponse3(Response3 response3) {
        mResponse3 = response3;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Exception getException() {
        return mException;
    }
}
