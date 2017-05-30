package com.example.administrator.policemap.dataEngin.cacheUtil;

import com.example.administrator.policemap.logging.FLog;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class DefaultCacheListener implements CacheEventListener {
    @Override
    public void onHit(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onHit");
    }

    @Override
    public void onMiss(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onMiss");
    }

    @Override
    public void onWriteAttempt(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onWriteAttempt");

    }

    @Override
    public void onWriteSuccess(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onWriteSuccess");

    }

    @Override
    public void onReadException(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onReadException");

    }

    @Override
    public void onWriteException(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onWriteException");

    }

    @Override
    public void onEviction(CacheEvent cacheEvent) {
        FLog.v(String.format("%s", cacheEvent.getCacheType().toString()),"onEviction");
    }

    @Override
    public void onCleared() {
        FLog.v("onCleared","onCleared");
    }
}
