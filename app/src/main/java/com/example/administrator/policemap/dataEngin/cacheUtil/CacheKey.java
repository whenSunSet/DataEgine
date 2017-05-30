package com.example.administrator.policemap.dataEngin.cacheUtil;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

/**
 * 一个强缓存键代替Object
 *
 */
public interface CacheKey {

    /**
     * 这个对于调试有用处
     * This is useful for instrumentation and debugging purposes. */
    String toString();

    /**
     * 这个方法需要被实现，否则cache key会使用引用地址来判断相同
     * This method must be implemented, otherwise the cache keys will be be compared by reference. */
    boolean equals(Object o);

    /**
     * 这个方法需要被实现，其被用在{@link #equals}方法中
     * This method must be implemented with accordance to the {@link #equals} method. */
    int hashCode();

}
