package com.example.administrator.policemap.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class UnitBaseEntity implements Parcelable {
    public int unitId;
    public String unitName;
    public int topNo;
    public int otherTopNo;
    public int defaultMission;
    public double locLng;
    public double locLat;
    public String wifiId;
    public byte unitType;
    public String createTime;

    public UnitBaseEntity(int unitId, String unitName, int topNo, int otherTopNo, int defaultMission, double locLng, double locLat, String wifiId, byte unitType, String createTime) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.topNo = topNo;
        this.otherTopNo = otherTopNo;
        this.defaultMission = defaultMission;
        this.locLng = locLng;
        this.locLat = locLat;
        this.wifiId = wifiId;
        this.unitType = unitType;
        this.createTime = createTime;
    }

    public UnitBaseEntity() {
    }

    @Override
    public String toString() {
        return "UnitBaseEntity{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", topNo=" + topNo +
                ", otherTopNo=" + otherTopNo +
                ", defaultMission=" + defaultMission +
                ", locLng=" + locLng +
                ", locLat=" + locLat +
                ", wifiId='" + wifiId + '\'' +
                ", unitType=" + unitType +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static class Request implements Parcelable {
        private int unitBaseId;
        private int unitId;

        public Request(int unitBaseId, int unitId) {
            this.unitBaseId = unitBaseId;
            this.unitId = unitId;
        }

        public int getUnitBaseId() {
            return unitBaseId;
        }

        public void setUnitBaseId(int unitBaseId) {
            this.unitBaseId = unitBaseId;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.unitBaseId);
            dest.writeInt(this.unitId);
        }

        protected Request(Parcel in) {
            this.unitBaseId = in.readInt();
            this.unitId = in.readInt();
        }

        public static final Creator<Request> CREATOR = new Creator<Request>() {
            @Override
            public Request createFromParcel(Parcel source) {
                return new Request(source);
            }

            @Override
            public Request[] newArray(int size) {
                return new Request[size];
            }
        };
    }

    public static class Response implements Parcelable {
        private List<UnitBaseEntity> mUnitBaseEntityList=new ArrayList<>();

        public Response(List<UnitBaseEntity> unitBaseEntityList) {
            mUnitBaseEntityList = unitBaseEntityList;
        }

        public List<UnitBaseEntity> getUnitBaseEntityList() {
            return mUnitBaseEntityList;
        }

        public void setUnitBaseEntityList(List<UnitBaseEntity> unitBaseEntityList) {
            mUnitBaseEntityList = unitBaseEntityList;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.mUnitBaseEntityList);
        }

        protected Response(Parcel in) {
            this.mUnitBaseEntityList = in.createTypedArrayList(UnitBaseEntity.CREATOR);
        }

        public static final Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel source) {
                return new Response(source);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.unitId);
        dest.writeString(this.unitName);
        dest.writeInt(this.topNo);
        dest.writeInt(this.otherTopNo);
        dest.writeDouble(this.locLng);
        dest.writeDouble(this.locLat);
        dest.writeString(this.wifiId);
        dest.writeByte(this.unitType);
        dest.writeString(this.createTime);
    }

    protected UnitBaseEntity(Parcel in) {
        this.unitId = in.readInt();
        this.unitName = in.readString();
        this.topNo = in.readInt();
        this.otherTopNo = in.readInt();
        this.locLng = in.readDouble();
        this.locLat = in.readDouble();
        this.wifiId = in.readString();
        this.unitType = in.readByte();
        this.createTime = in.readString();
    }

    public static final Creator<UnitBaseEntity> CREATOR = new Creator<UnitBaseEntity>() {
        @Override
        public UnitBaseEntity createFromParcel(Parcel source) {
            return new UnitBaseEntity(source);
        }

        @Override
        public UnitBaseEntity[] newArray(int size) {
            return new UnitBaseEntity[size];
        }
    };
}
