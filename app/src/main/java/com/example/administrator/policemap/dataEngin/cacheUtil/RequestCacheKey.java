package com.example.administrator.policemap.dataEngin.cacheUtil;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class RequestCacheKey<Request> implements CacheKey {
    final Request mRequest;

    public RequestCacheKey(Request request) {
        mRequest = request;
    }

    @Override
    public String toString() {
        return mRequest.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RequestCacheKey) {
            final RequestCacheKey<Request> otherKey = (RequestCacheKey<Request>) o;
            return mRequest.equals(otherKey.mRequest);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mRequest.hashCode();
    }

}
