package com.example.administrator.policemap.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class MissionInfoEntity implements Parcelable {
    public int missionId;
    public int postUnitId;
    public int checkPoint;
    public String checkIn;
    public String checkOut;
    public String missionDes;
    public int duration;
    public byte gainScore;
    public int pCount;
    public int nowPCount;
    public String notes;
    public String createTime;

    @Override
    public String toString() {
        return "MissionInfoEntity{" +
                "missionId=" + missionId +
                ", postUnitId=" + postUnitId +
                ", checkPoint=" + checkPoint +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", missionDes='" + missionDes + '\'' +
                ", duration=" + duration +
                ", gainScore=" + gainScore +
                ", pCount=" + pCount +
                ", nowPCount=" + nowPCount +
                ", notes='" + notes + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static class Request{
        private int unitId;

        public Request(int unitId) {
            this.unitId = unitId;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }
    }

    public static class MissionStatusEntity implements Parcelable {

        public static final int MISSION_START=0;
        public static final int MISSION_SIGN_IN_ED=1;
        public static final int MISSION_SIGN_OUT_ED=2;
        public static final int MISSION_END=3;

        private Response mResponse=new Response();
        private UnitBaseEntity mUnitBaseEntity;
        private int nowMissions=-1;
        private int canMissions=-1;
        private int status= MISSION_END;
        private int recordId= -1;

        public MissionStatusEntity() {

        }

        public MissionStatusEntity(Response response, UnitBaseEntity unitBaseEntity, int nowMissions, int canMissions, int status, int recordId) {
            mResponse = response;
            mUnitBaseEntity = unitBaseEntity;
            this.nowMissions = nowMissions;
            this.canMissions = canMissions;
            this.status = status;
            this.recordId = recordId;
        }

        public Response getResponse() {
            return mResponse;
        }

        public void setResponse(Response response) {
            mResponse = response;
        }

        public UnitBaseEntity getUnitBaseEntity() {
            return mUnitBaseEntity;
        }

        public void setUnitBaseEntity(UnitBaseEntity unitBaseEntity) {
            mUnitBaseEntity = unitBaseEntity;
        }

        public int getNowMissions() {
            return nowMissions;
        }

        public void setNowMissions(int nowMissions) {
            this.nowMissions = nowMissions;
        }

        public int getCanMissions() {
            return canMissions;
        }

        public void setCanMissions(int canMissions) {
            this.canMissions = canMissions;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.mResponse, flags);
            dest.writeParcelable(this.mUnitBaseEntity, flags);
            dest.writeInt(this.nowMissions);
            dest.writeInt(this.canMissions);
            dest.writeInt(this.status);
            dest.writeInt(this.recordId);
        }

        protected MissionStatusEntity(Parcel in) {
            this.mResponse = in.readParcelable(Response.class.getClassLoader());
            this.mUnitBaseEntity = in.readParcelable(UnitBaseEntity.class.getClassLoader());
            this.nowMissions = in.readInt();
            this.canMissions = in.readInt();
            this.status = in.readInt();
            this.recordId = in.readInt();
        }

        public static final Creator<MissionStatusEntity> CREATOR = new Creator<MissionStatusEntity>() {
            @Override
            public MissionStatusEntity createFromParcel(Parcel source) {
                return new MissionStatusEntity(source);
            }

            @Override
            public MissionStatusEntity[] newArray(int size) {
                return new MissionStatusEntity[size];
            }
        };
    }

    public static class MissionAndPolice{
        private MissionInfoEntity missionInfoEntity;
        private PolicePeopleEntity policePeopleEntity1;
        private PolicePeopleEntity policePeopleEntity2;

        public MissionAndPolice() {
        }

        public MissionAndPolice(MissionInfoEntity missionInfoEntity, PolicePeopleEntity policePeopleEntity1, PolicePeopleEntity policePeopleEntity2) {
            this.missionInfoEntity = missionInfoEntity;
            this.policePeopleEntity1 = policePeopleEntity1;
            this.policePeopleEntity2 = policePeopleEntity2;
        }

        public MissionInfoEntity getMissionInfoEntity() {
            return missionInfoEntity;
        }

        public void setMissionInfoEntity(MissionInfoEntity missionInfoEntity) {
            this.missionInfoEntity = missionInfoEntity;
        }

        public PolicePeopleEntity getPolicePeopleEntity1() {
            return policePeopleEntity1;
        }

        public void setPolicePeopleEntity1(PolicePeopleEntity policePeopleEntity1) {
            this.policePeopleEntity1 = policePeopleEntity1;
        }

        public PolicePeopleEntity getPolicePeopleEntity2() {
            return policePeopleEntity2;
        }

        public void setPolicePeopleEntity2(PolicePeopleEntity policePeopleEntity2) {
            this.policePeopleEntity2 = policePeopleEntity2;
        }
    }

    public static class MissionAndPoliceResponse{
        private List<MissionAndPolice> missionAndPoliceList=new ArrayList<>();

        public MissionAndPoliceResponse() {
        }

        public MissionAndPoliceResponse(List<MissionAndPolice> missionAndPoliceList) {
            this.missionAndPoliceList = missionAndPoliceList;
        }

        public List<MissionAndPolice> getMissionAndPoliceList() {
            return missionAndPoliceList;
        }

        public void setMissionAndPoliceList(List<MissionAndPolice> missionAndPoliceList) {
            this.missionAndPoliceList = missionAndPoliceList;
        }
    }

    public static class Response implements Parcelable {

        private List<MissionInfoEntity> mMissionInfoEntityList=new ArrayList<>();

        public Response() {
        }

        public Response(List<MissionInfoEntity> missionInfoEntityList) {
            mMissionInfoEntityList = missionInfoEntityList;
        }

        public List<MissionInfoEntity> getMissionInfoEntityList() {
            return mMissionInfoEntityList;
        }

        public void setMissionInfoEntityList(List<MissionInfoEntity> missionInfoEntityList) {
            mMissionInfoEntityList = missionInfoEntityList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.mMissionInfoEntityList);
        }

        protected Response(Parcel in) {
            this.mMissionInfoEntityList = in.createTypedArrayList(MissionInfoEntity.CREATOR);
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
        dest.writeInt(this.missionId);
        dest.writeInt(this.postUnitId);
        dest.writeInt(this.checkPoint);
        dest.writeString(this.checkIn);
        dest.writeString(this.checkOut);
        dest.writeString(this.missionDes);
        dest.writeInt(this.duration);
        dest.writeByte(this.gainScore);
        dest.writeInt(this.pCount);
        dest.writeInt(this.nowPCount);
        dest.writeString(this.notes);
        dest.writeString(this.createTime);
    }

    protected MissionInfoEntity(Parcel in) {
        this.missionId = in.readInt();
        this.postUnitId = in.readInt();
        this.checkPoint = in.readInt();
        this.checkIn = in.readString();
        this.checkOut = in.readString();
        this.missionDes = in.readString();
        this.duration = in.readInt();
        this.gainScore = in.readByte();
        this.pCount = in.readInt();
        this.nowPCount = in.readInt();
        this.notes = in.readString();
        this.createTime = in.readString();
    }

    public static final Creator<MissionInfoEntity> CREATOR = new Creator<MissionInfoEntity>() {
        @Override
        public MissionInfoEntity createFromParcel(Parcel source) {
            return new MissionInfoEntity(source);
        }

        @Override
        public MissionInfoEntity[] newArray(int size) {
            return new MissionInfoEntity[size];
        }
    };
}
