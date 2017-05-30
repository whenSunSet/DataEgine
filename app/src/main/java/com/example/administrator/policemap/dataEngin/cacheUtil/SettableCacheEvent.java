package com.example.administrator.policemap.dataEngin.cacheUtil;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

import java.io.IOException;

/**
 * 实现了{@link CacheEvent} ，允许的值设置,支持回收实例
 * (由于内存事件很频繁所以这里用了，享元模式，只提供两个实例，不断的回收重用)
 */
public class SettableCacheEvent implements CacheEvent {

    private static final Object RECYCLER_LOCK = new Object();
    private static final int MAX_RECYCLED = 5;

    private static SettableCacheEvent sFirstRecycledEvent;
    private static int sRecycledCount;

    private CacheKey mCacheKey;
    private String mResourceId;
    private long mItemSize;
    private long mCacheLimit;
    private long mCacheSize;
    private IOException mException;
    private CacheEvent.CacheType mCacheType;
    private SettableCacheEvent mNextRecycledEvent;

    public static SettableCacheEvent obtain() {
        synchronized (RECYCLER_LOCK) {
            if (sFirstRecycledEvent != null) {
                SettableCacheEvent eventToReuse = sFirstRecycledEvent;
                sFirstRecycledEvent = eventToReuse.mNextRecycledEvent;
                eventToReuse.mNextRecycledEvent = null;
                sRecycledCount--;
                return eventToReuse;
            }
        }
        return new SettableCacheEvent();
    }

    private SettableCacheEvent() {
    }

    public CacheKey getCacheKey() {
        return mCacheKey;
    }

    public SettableCacheEvent setCacheKey(CacheKey cacheKey) {
        mCacheKey = cacheKey;
        return this;
    }

    public String getResourceId() {
        return mResourceId;
    }

    public SettableCacheEvent setResourceId(String resourceId) {
        mResourceId = resourceId;
        return this;
    }

    public long getItemSize() {
        return mItemSize;
    }

    public SettableCacheEvent setItemSize(long itemSize) {
        mItemSize = itemSize;
        return this;
    }

    public long getCacheSize() {
        return mCacheSize;
    }

    public SettableCacheEvent setCacheSize(long cacheSize) {
        mCacheSize = cacheSize;
        return this;
    }

    public long getCacheLimit() {
        return mCacheLimit;
    }

    public SettableCacheEvent setCacheLimit(long cacheLimit) {
        mCacheLimit = cacheLimit;
        return this;
    }

    public IOException getException() {
        return mException;
    }

    public CacheType getCacheType() {
        return mCacheType;
    }

    public SettableCacheEvent setCacheType(CacheType cacheType) {
        mCacheType = cacheType;
        return this;
    }

    public SettableCacheEvent setException(IOException exception) {
        mException = exception;
        return this;
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            if (sRecycledCount < MAX_RECYCLED) {
                reset();
                sRecycledCount++;

                if (sFirstRecycledEvent != null) {
                    mNextRecycledEvent = sFirstRecycledEvent;
                }
                sFirstRecycledEvent = this;
            }
        }
    }

    private void reset() {
        mCacheKey = null;
        mResourceId = null;
        mItemSize = 0;
        mCacheLimit = 0;
        mCacheSize = 0;
        mException = null;
        mCacheKey = null;
    }
}
