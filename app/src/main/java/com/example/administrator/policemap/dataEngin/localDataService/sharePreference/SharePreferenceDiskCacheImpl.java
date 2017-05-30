package com.example.administrator.policemap.dataEngin.localDataService.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEvent;
import com.example.administrator.policemap.dataEngin.cacheUtil.CacheEventListener;
import com.example.administrator.policemap.dataEngin.cacheUtil.JsonUtil;
import com.example.administrator.policemap.dataEngin.cacheUtil.SettableCacheEvent;
import com.example.administrator.policemap.dataEngin.cacheUtil.SimpleCacheKey;
import com.example.administrator.policemap.dataEngin.localDataService.DiskCache;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by heshixiyang on 2016/5/3.
 */
@ThreadSafe
public class SharePreferenceDiskCacheImpl<K extends SimpleCacheKey> implements DiskCache<K>{
    private  SharedPreferences mSharedPreferences;
    private  SharedPreferences.Editor mEditor;
    private  CacheEventListener mCacheEventListener;

    public SharePreferenceDiskCacheImpl(Context context,CacheEventListener cacheEventListener) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mCacheEventListener=cacheEventListener;
    }

    @Override
    public synchronized Object cache(K key, Object value) {
        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.DiskCache);
        mCacheEventListener.onWriteSuccess(settableCacheEvent);
        settableCacheEvent.recycle();

        mEditor = mSharedPreferences.edit();
        Object oldValue=get(key);
        put(key.toString(), (String) value);
        mEditor.apply();

        return oldValue==null?value:oldValue;
    }

    @Override
    public synchronized String get(K key) {
        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.MemoryCache);

        String value=get(key.toString());

        if (value==null) mCacheEventListener.onMiss(settableCacheEvent);
        else mCacheEventListener.onHit(settableCacheEvent);

        settableCacheEvent.recycle();
        return value;
    }

    @Override
    public synchronized void remove(K key) {
        SettableCacheEvent settableCacheEvent=SettableCacheEvent
                .obtain()
                .setCacheKey(key)
                .setCacheType(CacheEvent.CacheType.DiskCache);
        mCacheEventListener.onEviction(settableCacheEvent);
        settableCacheEvent.recycle();
        put(key.toString(),"");
    }

    @Override
    public synchronized void clear() {
        mCacheEventListener.onCleared();
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.apply();
    }

    @Override
    public void setCacheEventListener(CacheEventListener cacheEventListener) {
        mCacheEventListener=cacheEventListener;
    }

    private String get(String key){

//        if (key==null)return null;
//        Object value = getModule(key, Object.class);
//        if (value==null)value=getString(key,"");
//        if (value==null)value=getInt(key,Integer.MIN_VALUE);
//        if (value==null)value=getLong(key,Long.MIN_VALUE);
//        if (value==null)value=getBoolean(key,false);
        return getString(key,"");
    }

    private void put(String key, String value){
        commitString(key,value);
//        if (value==null)return;
//        if (value instanceof Integer){
//            commitInt(key,(Integer)value);
//        }else if (value instanceof String){
//            commitString(key,(String)value);
//        }else if (value instanceof Long){
//            commitLong(key,(Long) value);
//        }else if (value instanceof Boolean){
//            commitBoolean(key,(Boolean)value);
//        }else {
//            saveModule(key,value);
//        }
    }

    private void commitString(String key, String value){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    private String getString(String key, String failValue){
        String s= mSharedPreferences.getString(key, failValue);
        return TextUtils.isEmpty(s)?null:s;
    }

    private void commitInt(String key, int value){
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    private Integer getInt(String key, int failValue){
        Integer i=mSharedPreferences.getInt(key, failValue);
        return i==Integer.MIN_VALUE?null:i;
    }

    private void commitLong(String key, long value){
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    private Long getLong(String key, long failValue) {
        Long l=mSharedPreferences.getLong(key, failValue);
        return l==Long.MIN_VALUE?null:l;
    }

    private Boolean getBoolean(String key,boolean failValue){
        Boolean b= mSharedPreferences.getBoolean(key, failValue);
        return b==failValue?null:b;
    }

    private void commitBoolean(String key, boolean value){
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    private void saveModule(String  key,Object o){
        commitString(key, JsonUtil.objectToJson(o));
    }

    private Object getModule(String  key,Class c){
        return JsonUtil.jsonToObject(getString(key,""),c);
    }

}

