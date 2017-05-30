package com.example.administrator.policemap.dataEngin.interception;

import com.example.administrator.policemap.dataEngin.DataEngine;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.logging.FLog;

import java.util.List;


/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class RealInterceptorChain implements Interceptor.Chain {
    public static String TAG="RealInterceptorChain";
    private static final Object RECYCLER_LOCK = new Object();
    private static int MAX_RECYCLED = 5;
    private static RealInterceptorChain sFirstRecycledChain;
    private static int sRecycledCount;

    private RealInterceptorChain mNextRecycledChain;

    private Request mRequest;
    private final List<Interceptor> interceptors;
    private int index=0;

    private RealInterceptorChain(Request request) {
        mRequest=request;
        this.interceptors = DataEngine.mInterceptors;
    }

    @Override
    public Object proceed() throws Exception {
        if (index >= interceptors.size()){
            throw new AssertionError();
        }
        Interceptor interceptor = interceptors.get(index);
        boolean isEnable=mRequest.getInterceptorIsEnable()[index];
        index++;
        return interceptor.intercept(this,isEnable);
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            if (sRecycledCount < MAX_RECYCLED) {
                reset();
                sRecycledCount++;

                if (sFirstRecycledChain != null) {
                    mNextRecycledChain = sFirstRecycledChain;
                }
                sFirstRecycledChain = this;
                FLog.v(TAG,"回收Chain  sRecycledCount:"+sRecycledCount);
            }
        }
    }

    public static RealInterceptorChain obtain(Request request) {
        synchronized (RECYCLER_LOCK) {
            if (sFirstRecycledChain != null) {
                RealInterceptorChain eventToReuse = sFirstRecycledChain;
                sFirstRecycledChain = eventToReuse.mNextRecycledChain;
                eventToReuse.mNextRecycledChain = null;
                eventToReuse.mRequest=request;
                sRecycledCount--;
                FLog.v(TAG,"从对象池中获取Chain  sRecycledCount:"+sRecycledCount);
                return eventToReuse;
            }
        }
        FLog.v(TAG,"对象池已空，创建一个Chain  sRecycledCount:"+sRecycledCount);
        return new RealInterceptorChain(request);
    }

    private void reset() {
        mRequest = null;
        index=0;
    }
}
