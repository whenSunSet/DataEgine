package com.example.administrator.policemap.dataEngin.requestAndResponse;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;
import com.example.administrator.policemap.dataEngin.cacheUtil.JsonUtil;
import com.example.administrator.policemap.dataEngin.cacheUtil.SimpleCacheKey;
import com.example.administrator.policemap.logging.FLog;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

/**
 * 数据请求的类，带有三个泛型，以便将返回的结果处理成，客户端需要的类型。
 *
 *{@param mRequestFlag} 这个参数是用来标记客户端使用了哪一个请求，传入时不可为 null
 *{@param mCacheKey} 这个参数是用来向内存缓存中获取或者存储数据用的，传入时不可为 null，不传入的时候，
 *                    如果有{@param mParam}会设置成其json形式，如果没有的话会设置成{@param mRequestFlag}的 toString
 *{@param mParam} 这个参数是请求的参数，传入时可以为 null
 *{@param isCacheToMemory} 这个参数用于判断内存缓存服务的下一个服务返回的结果是否需要被缓存，默认是需要
 *{@param isSaveToLocal} 这个参数用于判断本地存储服务的下一个服务返回的结果是否需要被缓存，默认是需要
 *{@param interceptorIsEnable} 这个参数是 用于设置拦截器上的服务是否有效，默认全部有效
 *
 */
public class Request {
    private static final String TAG="Request";
    private static final Object RECYCLER_LOCK = new Object();
    private static int MAX_RECYCLED = 5;
    private static Request sFirstRecycledRequest;
    private static int sRecycledCount;

    private Request mNextRecycledRequest;

    @NonNull
    private RequestFlag mRequestFlag;
    @NonNull
    private CacheKey mCacheKey;
    @Nullable
    private Object mParam;
    private boolean isCacheToMemory=true;
    private boolean isSaveToLocal=true;
    private boolean[] interceptorIsEnable=new boolean[]{true,true,true,true,true,true,true,true,true};

    public static Request setFlag(@NonNull RequestFlag requestFlag){
        return obtain(requestFlag,null,null,null,-1);
    }

    public static Request setFlagParam(@NonNull RequestFlag requestFlag, @NonNull Object param){
        return obtain(requestFlag,param,null,null,-1);
    }

    public static Request setFlagParamKey(@NonNull RequestFlag requestFlag, @NonNull Object param, @NonNull String key){
        return obtain(requestFlag,param,key,null,-1);
    }

    public static Request setFlagParamInterceptorIsEnable(@NonNull RequestFlag requestFlag, @NonNull Object param, @NonNull boolean[] interceptorIsEnable){
        return obtain(requestFlag,param,null,interceptorIsEnable,-1);
    }

    public static Request setFlagParamWhichServiceUnable(@NonNull RequestFlag requestFlag, @NonNull Object param, int whichUnable){
        return obtain(requestFlag,param,null,null,whichUnable);
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            if (sRecycledCount < MAX_RECYCLED) {
                reset();
                sRecycledCount++;

                if (sFirstRecycledRequest != null) {
                    mNextRecycledRequest= sFirstRecycledRequest;
                }
                FLog.v(TAG,"回收Request  sRecycledCount:"+sRecycledCount);
                sFirstRecycledRequest= this;
            }
        }
    }

    private static Request obtain(@NonNull RequestFlag requestFlag, Object param, String key, boolean[] interceptorIsEnable, int whichUnable) {
        synchronized (RECYCLER_LOCK) {
            if (sFirstRecycledRequest!= null) {
                Request requestToReuse = sFirstRecycledRequest;
                sFirstRecycledRequest= requestToReuse.mNextRecycledRequest;
                requestToReuse.mNextRecycledRequest= null;

                requestToReuse.mRequestFlag=requestFlag;
                if (param==null){
                    requestToReuse.mCacheKey=new SimpleCacheKey(requestFlag.toString());
                }else {
                    requestToReuse.mParam=param;
                    if (key!=null){
                        requestToReuse.mCacheKey = new SimpleCacheKey(key);
                    }else {
                        requestToReuse.mCacheKey=new SimpleCacheKey(JsonUtil.objectToJson(param));
                        if (interceptorIsEnable!=null) {
                            requestToReuse.interceptorIsEnable = interceptorIsEnable;
                        }else {
                            if (whichUnable!=-1)requestToReuse.interceptorIsEnable[whichUnable]=false;
                        }
                    }
                }
                sRecycledCount--;
                FLog.v(TAG,"从对象池中获取Request  sRecycledCount:"+sRecycledCount);
                return requestToReuse;
            }
        }
        FLog.v(TAG,"对象池已空，创建一个Request  sRecycledCount:"+sRecycledCount);
        if (param==null){
            return new Request(requestFlag);
        }else {
            if (key!=null){
                return new Request(requestFlag,param,key);
            }else {
                if (interceptorIsEnable!=null) {
                    return new Request(requestFlag,param,interceptorIsEnable);
                }else {
                    if (whichUnable==-1){
                        return new Request(requestFlag,param);
                    }else {
                        return new Request(requestFlag,param,whichUnable);
                    }
                }
            }
        }
    }

    private void reset() {
        mRequestFlag=null;
        mCacheKey=null;
        mParam=null;
        isCacheToMemory=true;
        isSaveToLocal=true;
        interceptorIsEnable=new boolean[]{true,true,true,true,true,true,true,true,true};
    }

    private Request() {
    }

    private Request(@NonNull RequestFlag requestFlag) {
        mRequestFlag = requestFlag;
        mCacheKey=new SimpleCacheKey(mRequestFlag.toString());
    }

    private Request(@NonNull RequestFlag requestFlag, @Nullable Object param) {
        mRequestFlag = requestFlag;
        mParam = param;
        mCacheKey=new SimpleCacheKey(JsonUtil.objectToJson(param));
    }

    private Request(@NonNull RequestFlag requestFlag, @Nullable Object param, boolean[] interceptorIsEnable) {
        mRequestFlag = requestFlag;
        mParam = param;
        this.interceptorIsEnable = interceptorIsEnable;
        mCacheKey=new SimpleCacheKey(JsonUtil.objectToJson(param));
    }

    private Request(@NonNull RequestFlag requestFlag, @Nullable Object param, int whichUnable) {
        mRequestFlag = requestFlag;
        mParam = param;
        interceptorIsEnable[whichUnable]=false;
        mCacheKey=new SimpleCacheKey(JsonUtil.objectToJson(param));
    }

    private Request(@NonNull RequestFlag requestFlag, @Nullable Object param, String key) {
        mCacheKey = new SimpleCacheKey(key);
        mRequestFlag = requestFlag;
        mParam = param;
    }

    public Request setParam(@Nullable Object param) {
        this.mParam = param;
        return this;
    }

    public Request setRequestFlag(@NonNull RequestFlag requestFlag) {
        mRequestFlag = requestFlag;
        return this;
    }

    public Request setServiceIsEnable(int serviceNum, boolean isEnable) {
        if (serviceNum<interceptorIsEnable.length)interceptorIsEnable[serviceNum]=isEnable;
        return this;
    }

    public Request setInterceptorIsEnable(boolean[] interceptorIsEnable) {
        this.interceptorIsEnable = interceptorIsEnable;
        return this;
    }

    public boolean getWhichServiceIsEnable(int serviceNum) {
        return serviceNum < interceptorIsEnable.length && interceptorIsEnable[serviceNum];
    }

    public Request setCacheKey(@NonNull CacheKey cacheKey) {
        mCacheKey = cacheKey;
        return this;
    }

    public Request setCacheToMemory(boolean cacheToMemory) {
        isCacheToMemory = cacheToMemory;
        return this;
    }

    public Request setSaveToLocal(boolean saveToLocal) {
        isSaveToLocal = saveToLocal;
        return this;
    }

    public boolean[] getInterceptorIsEnable() {
        return interceptorIsEnable;
    }

    @Nullable
    public Object getParam() {
        return mParam;
    }

    @NonNull
    public RequestFlag getRequestFlag() {
        return mRequestFlag;
    }

    @NonNull
    public CacheKey getCacheKey() {
        return mCacheKey;
    }

    public boolean isCacheToMemory() {
        return isCacheToMemory;
    }

    public boolean isSaveToLocal() {
        return isSaveToLocal;
    }

    @Override
    public String toString() {
        return "Request：{" +
                "mCacheKey=" + mCacheKey +
                ", mRequestFlag=" + mRequestFlag +
                ", mParam=" + mParam +
                ", isCacheToMemory=" + isCacheToMemory +
                ", isSaveToLocal=" + isSaveToLocal +
                ", interceptorIsEnable=" + Arrays.toString(interceptorIsEnable) +
                '}';
    }
}
