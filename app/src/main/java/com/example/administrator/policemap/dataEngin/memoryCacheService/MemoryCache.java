package com.example.administrator.policemap.dataEngin.memoryCacheService;

/**
 * Created by heshixiyang on 2017/3/9.
 */

import com.android.internal.util.Predicate;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEventListener;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;

/**
 * 内存缓存接口
 * @param <K>  key
 */
public interface MemoryCache<K extends CacheKey,V>{

    /**
     * 缓存 String类型的json
     * 如果是更新的话就返回老的value，否则返回新的value
     * @param key
     * @param value
     * @return
     */
    V cache(K key, V value);

    /**
     * 获取缓存，如果没有就返回null
     * @param key
     * @return
     */
    V get(K key);

    V remove(K key);

    /**
     * 删除所有能够和传入Predicate匹配的cache key的缓存
     * @param predicate returns true if an item with the given key should be removed
     * @return 移除的缓存数据的条数
     */
    int removeAll(Predicate<K> predicate);

    /**
     * 查询是否有存在内存缓存的cache key与传入的Predicate匹配
     *
     * @param predicate 给定的匹配规则
     * @return 匹配返回true，否则返回false
     */
    boolean contains(Predicate<K> predicate);

    int clear();

    void setCacheEventListener(CacheEventListener cacheEventListener);
}
