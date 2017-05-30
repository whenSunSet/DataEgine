package com.example.administrator.policemap.dataEngin.memoryCacheService;

import com.example.administrator.policemap.dataEngin.cacheUtil.CacheKey;
import com.example.administrator.policemap.dataEngin.cacheUtil.DefaultCacheListener;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.memoryCacheService.countingLruMapCache.CountingLruMapMemoryCacheImpl;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by heshixiyang on 2017/5/21.
 */
@ThreadSafe
public class MemoryCacheServiceImpl implements Service{
    private final MemoryCache<CacheKey,Object> mainMemoryCache=new CountingLruMapMemoryCacheImpl<CacheKey, Object>(200,new DefaultCacheListener());
    private final MemoryCache minorMemoryCache=null;
    private boolean enable;

    public MemoryCacheServiceImpl(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Object in(Request request, Object in) throws Exception {
        return mainMemoryCache.cache(request.getCacheKey(),in==null?request.getParam():in);
    }

    @Override
    public Object out(Request request) throws Exception {
        return mainMemoryCache.get(request.getCacheKey());
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable=enable;
    }

}
