package com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity;

import com.example.administrator.policemap.httpEntity.UnitBaseEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Entity
public class UnitBase {
    @Id(autoincrement = true)
    @Property(nameInDb = "unit_id")
    private long unitId;

    @NotNull
    @Property(nameInDb = "unit_name")
    private String unitName;

    @NotNull
    @Property(nameInDb = "top_no")
    private int topNo;

    @NotNull
    @Property(nameInDb = "other_top_no")
    private int otherTopNo;

    @NotNull
    @Property(nameInDb = "loc_lng")
    private double locLng;

    @NotNull
    @Property(nameInDb = "loc_lat")
    private double locLat;

    @NotNull
    @Property(nameInDb = "wifi_id")
    private String wifiId;

    @NotNull
    @Property(nameInDb = "unit_type")
    private byte unitType;

    @NotNull
    @Property(nameInDb = "default_mission")
    private int defaultMission;

    @NotNull
    @Property(nameInDb = "create_time")
    private Date createTime;


    public UnitBase(UnitBaseEntity unitBaseEntity) {
        this.unitId = unitBaseEntity.unitId;
        this.unitName = unitBaseEntity.unitName;
        this.topNo = unitBaseEntity.topNo;
        this.otherTopNo = unitBaseEntity.otherTopNo;
        this.locLng = unitBaseEntity.locLng;
        this.locLat = unitBaseEntity.locLat;
        this.wifiId = unitBaseEntity.wifiId;
        this.unitType = unitBaseEntity.unitType;
        this.defaultMission = unitBaseEntity.defaultMission;
        this.createTime = new Date();
    }

    @Generated(hash = 1914605490)
    public UnitBase(long unitId, @NotNull String unitName, int topNo, int otherTopNo, double locLng,
            double locLat, @NotNull String wifiId, byte unitType, int defaultMission,
            @NotNull Date createTime) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.topNo = topNo;
        this.otherTopNo = otherTopNo;
        this.locLng = locLng;
        this.locLat = locLat;
        this.wifiId = wifiId;
        this.unitType = unitType;
        this.defaultMission = defaultMission;
        this.createTime = createTime;
    }

    @Generated(hash = 1040701462)
    public UnitBase() {
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getTopNo() {
        return topNo;
    }

    public void setTopNo(int topNo) {
        this.topNo = topNo;
    }

    public int getOtherTopNo() {
        return otherTopNo;
    }

    public void setOtherTopNo(int otherTopNo) {
        this.otherTopNo = otherTopNo;
    }

    public double getLocLng() {
        return locLng;
    }

    public void setLocLng(double locLng) {
        this.locLng = locLng;
    }

    public double getLocLat() {
        return locLat;
    }

    public void setLocLat(double locLat) {
        this.locLat = locLat;
    }

    public String getWifiId() {
        return wifiId;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public byte getUnitType() {
        return unitType;
    }

    public void setUnitType(byte unitType) {
        this.unitType = unitType;
    }

    public int getDefaultMission() {
        return defaultMission;
    }

    public void setDefaultMission(int defaultMission) {
        this.defaultMission = defaultMission;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
}
