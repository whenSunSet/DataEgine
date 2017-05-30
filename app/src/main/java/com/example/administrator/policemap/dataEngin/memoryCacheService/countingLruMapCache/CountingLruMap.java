package com.example.administrator.policemap.dataEngin.memoryCacheService.countingLruMapCache;

/**
 * Created by heshixiyang on 2017/3/26.
 */

import android.support.annotation.VisibleForTesting;

import com.android.internal.util.Predicate;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 一个用于储存缓存的LinkedHashMap的包装类
 * 由于LinkedHashMap维持这一个顺序Key，所以我们可以按插入时间的先后，进行LRU内存清理
 * Map that keeps track of the elements order (according to the LRU policy) and their size.
 */
@ThreadSafe
class CountingLruMap<K extends CacheKey,V> {

    @GuardedBy("this")
    private final LinkedHashMap<K,V> mMap = new LinkedHashMap<>();

    private final int maxCacheSize;

    public CountingLruMap(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    synchronized ArrayList<K> getKeys() {
        return new ArrayList<>(mMap.keySet());
    }

    public synchronized int getCount() {
        return mMap.size();
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    @Nullable
    public synchronized K getFirstKey() {
        return mMap.isEmpty() ? null : mMap.keySet().iterator().next();
    }

    public synchronized ArrayList<LinkedHashMap.Entry<K,V>> getMatchingEntries(@Nullable Predicate<K> predicate) {
        ArrayList<LinkedHashMap.Entry<K, V>> matchingEntries = new ArrayList<>(mMap.entrySet().size());
        for (LinkedHashMap.Entry<K, V> entry : mMap.entrySet()) {
            if (predicate == null || predicate.apply(entry.getKey())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    public synchronized boolean contains(K key) {
        return mMap.containsKey(key);
    }

    @Nullable
    public synchronized V get(K key) {
        V value = mMap.remove(key);
        mMap.put(key, value);
        return value;
    }

    @Nullable
    public synchronized V  put(K key, V value) {
        V oldValue = mMap.remove(key);
        mMap.put(key, value);
        return oldValue==null?value:oldValue;
    }

    @Nullable
    public synchronized V remove(K key) {
        V oldValue = mMap.remove(key);
        return oldValue;
    }

    public synchronized ArrayList<K> removeAll(@Nullable Predicate<K> predicate) {
        ArrayList<K> oldKey = new ArrayList<>();
        Iterator<LinkedHashMap.Entry<K, V>> iterator = mMap.entrySet().iterator();
        while (iterator.hasNext()) {
            LinkedHashMap.Entry<K, V> entry = iterator.next();
            if (predicate == null || predicate.apply(entry.getKey())) {
                oldKey.add(entry.getKey());
                iterator.remove();
            }
        }
        return oldKey;
    }

    public synchronized ArrayList<V> clear() {
        ArrayList<V> oldValues = new ArrayList<>(mMap.values());
        mMap.clear();
        return oldValues;
    }

}

