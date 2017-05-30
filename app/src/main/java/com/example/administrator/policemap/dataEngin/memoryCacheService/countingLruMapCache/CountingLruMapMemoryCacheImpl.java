package com.example.administrator.policemap.dataEngin.memoryCacheService.countingLruMapCache;

import com.android.internal.util.Predicate;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEvent;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEventListener;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;
import com.example.administrator.policemap.dataEngin.cacheUtil.SettableCacheEvent;
import com.example.administrator.policemap.dataEngin.memoryCacheService.MemoryCache;

import java.util.ArrayList;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@ThreadSafe
public class CountingLruMapMemoryCacheImpl<K extends CacheKey,V> implements MemoryCache<K,V> {
    @GuardedBy("this")
    private final CountingLruMap<K,V> mCachedEntries;
    private CacheEventListener mCacheEventListener;

    public CountingLruMapMemoryCacheImpl(int maxCacheSize, CacheEventListener cacheEventListener) {
        mCachedEntries = new CountingLruMap<K,V>(maxCacheSize);
        mCacheEventListener=cacheEventListener;
    }

    @Override
    public V cache(K key, V value) {
        if (mCachedEntries.getCount()>mCachedEntries.getMaxCacheSize()){
            K oldKey=mCachedEntries.getFirstKey();
            remove(oldKey);
        }

        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.MemoryCache);
        mCacheEventListener.onWriteSuccess(settableCacheEvent);
        settableCacheEvent.recycle();

        return mCachedEntries.put(key,value);
    }

    @Override
    public V get(K key) {
        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.MemoryCache);

        V value=mCachedEntries.get(key);

        if (value==null) mCacheEventListener.onMiss(settableCacheEvent);
        else mCacheEventListener.onHit(settableCacheEvent);

        settableCacheEvent.recycle();
        return value;
    }

    @Override
    public V remove(K key) {
        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.MemoryCache);
        mCacheEventListener.onEviction(settableCacheEvent);
        settableCacheEvent.recycle();

        return mCachedEntries.remove(key);
    }

    @Override
    public int removeAll(Predicate<K> predicate) {
        ArrayList<K> kArrayList=mCachedEntries.removeAll(predicate);
        for (int i = 0; i < kArrayList.size(); i++) {
            SettableCacheEvent settableCacheEvent=SettableCacheEvent
                    .obtain()
                    .setCacheKey(kArrayList.get(i))
                    .setCacheType(CacheEvent.CacheType.MemoryCache);
            mCacheEventListener.onEviction(settableCacheEvent);
        }
        return kArrayList.size();
    }

    @Override
    public boolean contains(Predicate<K> predicate) {
        return !mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    @Override
    public int clear() {
        mCacheEventListener.onCleared();
        return mCachedEntries.clear().size();
    }

    @Override
    public void setCacheEventListener(CacheEventListener cacheEventListener) {
        if (cacheEventListener!=null)mCacheEventListener=cacheEventListener;
    }

}
