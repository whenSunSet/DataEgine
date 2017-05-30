package com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity;

import com.example.administrator.policemap.httpEntity.MissionInfoEntity;
import com.example.administrator.policemap.util.DateUtils;

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
public class MissionInfo {
    @Id(autoincrement = true)
    @Property(nameInDb = "mission_id")
    private long missionId;

    @NotNull
    @Property(nameInDb = "post_unit_id")
    private int postUnitId;

    @NotNull
    @Property(nameInDb = "check_point")
    private int checkPoint;

    @Property(nameInDb = "check_in")
    private Date checkIn;

    @Property(nameInDb = "check_out")
    private Date checkOut;

    @NotNull
    @Property(nameInDb = "mission_des")
    private String missionDes;

    @NotNull
    @Property(nameInDb = "duration")
    private int duration;

    @NotNull
    @Property(nameInDb = "gain_score")
    private byte gainScore;

    @NotNull
    @Property(nameInDb = "p_count")
    private int pCount;

    @NotNull
    @Property(nameInDb = "notes")
    private String notes;

    @NotNull
    @Property(nameInDb = "now_p_count")
    private int nowPCount;

    @NotNull
    @Property(nameInDb = "create_time")
    private Date createTime;

    public MissionInfo(MissionInfoEntity missionInfoEntity){
        this.missionId = missionInfoEntity.missionId;
        this.postUnitId = missionInfoEntity.postUnitId;
        this.checkPoint = missionInfoEntity.checkPoint;
        this.checkIn = DateUtils.stringToDate(missionInfoEntity.checkIn,DateUtils.yyyyMMddHHmm);
        this.checkOut = DateUtils.stringToDate(missionInfoEntity.checkOut,DateUtils.yyyyMMddHHmm);
        this.missionDes = missionInfoEntity.missionDes;
        this.duration = missionInfoEntity.duration;
        this.gainScore = missionInfoEntity.gainScore;
        this.pCount = missionInfoEntity.pCount;
        this.notes = missionInfoEntity.notes;
        this.nowPCount = missionInfoEntity.nowPCount;
        this.createTime = new Date();
    }

    @Generated(hash = 1397752950)
    public MissionInfo(long missionId, int postUnitId, int checkPoint, Date checkIn, Date checkOut,
            @NotNull String missionDes, int duration, byte gainScore, int pCount,
            @NotNull String notes, int nowPCount, @NotNull Date createTime) {
        this.missionId = missionId;
        this.postUnitId = postUnitId;
        this.checkPoint = checkPoint;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.missionDes = missionDes;
        this.duration = duration;
        this.gainScore = gainScore;
        this.pCount = pCount;
        this.notes = notes;
        this.nowPCount = nowPCount;
        this.createTime = createTime;
    }

    @Generated(hash = 214392362)
    public MissionInfo() {
    }

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getPostUnitId() {
        return postUnitId;
    }

    public void setPostUnitId(int postUnitId) {
        this.postUnitId = postUnitId;
    }

    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getMissionDes() {
        return missionDes;
    }

    public void setMissionDes(String missionDes) {
        this.missionDes = missionDes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public byte getGainScore() {
        return gainScore;
    }

    public void setGainScore(byte gainScore) {
        this.gainScore = gainScore;
    }

    public int getPCount() {
        return pCount;
    }

    public void setPCount(int pCount) {
        this.pCount = pCount;
    }

    public String getNote() {
        return notes;
    }

    public void setNote(String note) {
        this.notes = note;
    }

    public int getNowPCount() {
        return nowPCount;
    }

    public void setNowPCount(int nowPCount) {
        this.nowPCount = nowPCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }
}
