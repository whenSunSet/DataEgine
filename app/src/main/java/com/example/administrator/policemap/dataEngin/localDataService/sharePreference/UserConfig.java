package com.example.administrator.policemap.dataEngin.localDataService.sharePreference;

import com.example.administrator.policemap.httpEntity.LoginEntity;
import com.example.administrator.policemap.httpEntity.UnitBaseEntity;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class UserConfig {

    private String rememberedPhone;
    private String rememberedPassword;
    private LoginEntity.UserEntity mUserEntity;
    private String unitName;
    private int otherTopUnitId;
    private String token;
    private UnitBaseEntity psbStation;
    private UnitBaseEntity policeStation;

    public UserConfig(String rememberedPhone, String rememberedPassword, LoginEntity.UserEntity userEntity, String unitName, int otherTopUnitId, String token) {
        this.rememberedPhone = rememberedPhone;
        this.rememberedPassword = rememberedPassword;
        this.mUserEntity = userEntity;
        this.unitName = unitName;
        this.otherTopUnitId = otherTopUnitId;
        this.token = token;
    }

    public UserConfig(String rememberedPhone, String rememberedPassword, LoginEntity.UserEntity userEntity, String unitName, int otherTopUnitId, String token, UnitBaseEntity psbStation, UnitBaseEntity policeStation) {
        this.rememberedPhone = rememberedPhone;
        this.rememberedPassword = rememberedPassword;
        mUserEntity = userEntity;
        this.unitName = unitName;
        this.otherTopUnitId = otherTopUnitId;
        this.token = token;
        this.psbStation = psbStation;
        this.policeStation = policeStation;
    }

    public String getRememberedPhone() {
        return rememberedPhone;
    }

    public void setRememberedPhone(String rememberedPhone) {
        this.rememberedPhone = rememberedPhone;
    }

    public String getRememberedPassword() {
        return rememberedPassword;
    }

    public void setRememberedPassword(String rememberedPassword) {
        this.rememberedPassword = rememberedPassword;
    }

    public LoginEntity.UserEntity getUserEntity() {
        return mUserEntity;
    }

    public void setUserEntity(LoginEntity.UserEntity userEntity) {
        mUserEntity = userEntity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getOtherTopUnitId() {
        return otherTopUnitId;
    }

    public void setOtherTopUnitId(int otherTopUnitId) {
        this.otherTopUnitId = otherTopUnitId;
    }

    public UnitBaseEntity getPsbStation() {
        return psbStation;
    }

    public void setPsbStation(UnitBaseEntity psbStation) {
        this.psbStation = psbStation;
    }

    public UnitBaseEntity getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(UnitBaseEntity policeStation) {
        this.policeStation = policeStation;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "rememberedPhone='" + rememberedPhone + '\'' +
                ", rememberedPassword='" + rememberedPassword + '\'' +
                ", mUserEntity=" + mUserEntity +
                ", unitName='" + unitName + '\'' +
                ", otherTopUnitId=" + otherTopUnitId +
                ", token='" + token + '\'' +
                ", psbStation=" + psbStation +
                ", policeStation=" + policeStation +
                '}';
    }
}
