package com.example.administrator.policemap.dataEngin.cacheUtil;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

/**
 * {@link CacheKey}的实现者，这个只是简单的包装了{@link String}
 * 使用这个CacheKey需要构建一个唯一的string，并且明确的表示缓存资源
 */
public class SimpleCacheKey implements CacheKey {
    final String mKey;

    public SimpleCacheKey(final String key) {
        mKey = key;
    }

    @Override
    public String toString() {
        return mKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SimpleCacheKey) {
            final SimpleCacheKey otherKey = (SimpleCacheKey) o;
            return mKey.equals(otherKey.mKey);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mKey.hashCode();
    }

}
