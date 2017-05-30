package com.example.administrator.policemap.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class LoginEntity {
    private String phone;
    private String password;
    public LoginEntity(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static class Response{
        private UserEntity userEntity;
        private String unitName;
        private int otherTopUnitId;
        private int result;
        private String  token;

        public Response(UserEntity userEntity, String unitName, int otherTopUnitId, int result, String token) {
            this.userEntity = userEntity;
            this.unitName = unitName;
            this.otherTopUnitId = otherTopUnitId;
            this.result = result;
            this.token = token;
        }

        public UserEntity getUserEntity() {
            return userEntity;
        }

        public void setUserEntity(UserEntity userEntity) {
            this.userEntity = userEntity;
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

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class UserEntity implements Parcelable {

        public int patrolId;
        public String patrolMobile;
        public String  patrolSid="";
        public byte patrolAge;
        public String gender="";
        public int unitId;
        public String patrolRealName="";
        public String pCommunity;
        public String pUnitName="";
        public byte patrolState;
        public String notes="";
        public String createTime;

        public UserEntity(int patrolId, String patrolMobile, String patrolSid, byte patrolAge, String gender) {
            this.patrolId = patrolId;
            this.patrolMobile = patrolMobile;
            this.patrolSid = patrolSid;
            this.patrolAge = patrolAge;
            this.gender = gender;
        }

        public UserEntity(int patrolId, String patrolMobile, String patrolSid, byte patrolAge, String gender, int unitId, String patrolRealName, String pCommunity, String pUnitName, byte patrolState, String notes, String createTime) {
            this.patrolId = patrolId;
            this.patrolMobile = patrolMobile;
            this.patrolSid = patrolSid;
            this.patrolAge = patrolAge;
            this.gender = gender;
            this.unitId = unitId;
            this.patrolRealName = patrolRealName;
            this.pCommunity = pCommunity;
            this.pUnitName = pUnitName;
            this.patrolState = patrolState;
            this.notes = notes;
            this.createTime = createTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.patrolId);
            dest.writeString(this.patrolMobile);
            dest.writeString(this.patrolSid);
            dest.writeByte(this.patrolAge);
            dest.writeString(this.gender);
            dest.writeInt(this.unitId);
            dest.writeString(this.patrolRealName);
            dest.writeString(this.pCommunity);
            dest.writeString(this.pUnitName);
            dest.writeByte(this.patrolState);
            dest.writeString(this.notes);
            dest.writeString(this.createTime);
        }

        protected UserEntity(Parcel in) {
            this.patrolId = in.readInt();
            this.patrolMobile = in.readString();
            this.patrolSid = in.readString();
            this.patrolAge = in.readByte();
            this.gender = in.readString();
            this.unitId = in.readInt();
            this.patrolRealName = in.readString();
            this.pCommunity = in.readString();
            this.pUnitName = in.readString();
            this.patrolState = in.readByte();
            this.notes = in.readString();
            this.createTime = in.readString();
        }

        public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
            @Override
            public UserEntity createFromParcel(Parcel source) {
                return new UserEntity(source);
            }

            @Override
            public UserEntity[] newArray(int size) {
                return new UserEntity[size];
            }
        };
    }
}
