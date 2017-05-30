package com.example.administrator.policemap.dataEngin.localDataService;


import com.example.administrator.policemap.MyApplication;
import com.example.administrator.policemap.dataEngin.cacheUtil.DefaultCacheListener;
import com.example.administrator.policemap.dataEngin.cacheUtil.JsonUtil;
import com.example.administrator.policemap.dataEngin.cacheUtil.SimpleCacheKey;
import com.example.administrator.policemap.dataEngin.interception.Service;
import com.example.administrator.policemap.dataEngin.localDataService.database.AppConfig;
import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.DaoMaster;
import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity.DBMap;
import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity.MissionInfo;
import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity.PolicePeople;
import com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity.UnitBase;
import com.example.administrator.policemap.dataEngin.localDataService.sharePreference.SharePreferenceDiskCacheImpl;
import com.example.administrator.policemap.dataEngin.localDataService.sharePreference.UserConfig;
import com.example.administrator.policemap.dataEngin.requestAndResponse.Request;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToLocalDataRequest;
import com.example.administrator.policemap.dataEngin.requestAndResponse.ToNetworkRequest;
import com.example.administrator.policemap.dataEngin.transformData.TransformDataMethodDispatch;
import com.example.administrator.policemap.httpEntity.FlushData;
import com.example.administrator.policemap.httpEntity.MissionInfoEntity;
import com.example.administrator.policemap.httpEntity.PolicePeopleEntity;
import com.example.administrator.policemap.httpEntity.UnitBaseEntity;
import com.example.administrator.policemap.logging.FLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by heshixiyang on 2017/5/21.
 */
@ThreadSafe
public class LocalDataServiceImpl implements Service,ToLocalDataRequest,ToNetworkRequest{
    private static String TAG="LocalDataServiceImpl";

    private final DaoMaster daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(MyApplication.myApplication, "my.db", null).getWritableDb());
    private final DiskCache<SimpleCacheKey> sharePreferenceDiskCache =new SharePreferenceDiskCacheImpl<>(MyApplication.myApplication,new DefaultCacheListener());
    private boolean enable;

    public LocalDataServiceImpl(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Object in(Request request,Object in) throws Exception {
        return TransformDataMethodDispatch.dispatch(this,this,request,in);
    }

    @Override
    public Object out(Request request) throws Exception {
        return TransformDataMethodDispatch.dispatch(this,this,request,null);
    }

    @Override
    public Object patrolAccountAction_login(Request request, Object in) throws IOException {
        return null;
    }

    @Override
    public Object initAppData(Request request, Object in) throws IOException {
        if (in==null)return null;
        FlushData flushData=(FlushData)in;
        ArrayList<MissionInfoEntity> mInfoEntityArrayList=flushData.getFlushData().getInfoEntityArrayList();
        ArrayList<PolicePeopleEntity> mPeopleArrayList=flushData.getFlushData().getPeopleArrayList();
        ArrayList<UnitBaseEntity> mUnitBaseArrayList=flushData.getFlushData().getUnitBaseArrayList();

        if (mInfoEntityArrayList!=null&&mInfoEntityArrayList.size()!=0){
            for (MissionInfoEntity m:mInfoEntityArrayList){
                daoMaster.newSession().getMissionInfoDao().insert(new MissionInfo(m));
            }
        }

        if (mPeopleArrayList!=null&&mPeopleArrayList.size()!=0){
            for (PolicePeopleEntity p :mPeopleArrayList){
                daoMaster.newSession().getPolicePeopleDao().insert(new PolicePeople(p));
            }
        }

        if (mUnitBaseArrayList!=null&&mUnitBaseArrayList.size()!=0){
            for (UnitBaseEntity u :mUnitBaseArrayList){
                daoMaster.newSession().getUnitBaseDao().insert(new UnitBase(u));
            }
        }
        request.setCacheToMemory(false);
        FLog.v(TAG,"向数据库初始化数据成功");
        return in;
    }

    @Override
    public Object updateAndGetAppConfig(Request request, Object in) throws Exception {
        if (request.getParam()==null){
            FLog.e(TAG,"appConfig插入时为null");
            throw new NullPointerException();
        }
        AppConfig appConfig=(AppConfig)request.getParam();
        List<DBMap> dbMapList=appConfig.transformToDBMap();
        daoMaster.newSession().getDBMapDao().insertOrReplaceInTx(dbMapList);
        FLog.v(TAG,"向数据库更新appConfig成功");
        return appConfig;
    }

    @Override
    public Object getAppConfig(Request request, Object in){
        AppConfig appConfig = null;
        List<DBMap> dbMapList=daoMaster.newSession().getDBMapDao().loadAll();
        if (dbMapList==null||dbMapList.size()==0)return null;
        appConfig=new AppConfig(dbMapList);
        FLog.v(TAG,"从数据库获取的appConfig");
        return appConfig;
    }

    @Override
    public Object updateAndGetUserConfig(Request request, Object in) throws Exception {
        if (request.getParam()==null){
            FLog.e(TAG,"userConfig插入时为null");
            throw new NullPointerException();
        }
        UserConfig userConfig=(UserConfig) request.getParam();
        sharePreferenceDiskCache.cache((SimpleCacheKey) request.getCacheKey(),JsonUtil.objectToJson(userConfig));
        FLog.v(TAG,"向sharePreferenceDiskCache更新userConfig成功");
        return userConfig;
    }

    @Override
    public Object getUserConfig(Request request, Object in) throws IOException {
        UserConfig userConfig= JsonUtil.jsonToObject((String) sharePreferenceDiskCache.get((SimpleCacheKey) request.getCacheKey()),UserConfig.class);
        FLog.v(TAG,"从sharePreferenceDiskCache获取的userConfig");
        return userConfig;
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
