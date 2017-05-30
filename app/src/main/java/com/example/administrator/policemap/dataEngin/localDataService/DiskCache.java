package com.example.administrator.policemap.dataEngin.localDataService;

import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEventListener;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;

import java.io.IOException;

/**
 * Created by heshixiyang on 2017/5/22.
 */
public interface DiskCache<K extends CacheKey> {
    /**
     * 缓存 String类型的json
     * 如果是更新的话就返回老的value，否则返回新的value
     * @param key
     * @param value
     * @return
     */
    Object cache( K key, Object value) throws IOException;

    /**
     * 获取缓存，如果没有就返回null
     * @param key
     * @return
     */
    Object get(K key);

    void remove(K key);

    void clear();

    void setCacheEventListener(CacheEventListener cacheEventListener);
}
