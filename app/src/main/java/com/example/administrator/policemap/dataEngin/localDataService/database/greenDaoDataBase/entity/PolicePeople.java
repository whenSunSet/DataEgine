package com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity;

import com.example.administrator.policemap.httpEntity.PolicePeopleEntity;

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
public class PolicePeople {

    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private long id;

    @NotNull
    @Property(nameInDb = "police_name")
    private String policeName;

    @NotNull
    @Property(nameInDb = "police_phone")
    private String policePhone;

    @Property(nameInDb = "unit_id")
    private int unitId;

    @NotNull
    @Property(nameInDb = "create_time")
    private Date createTime;

    public PolicePeople(PolicePeopleEntity policePeopleEntity) {
        this.id = policePeopleEntity.getId();
        this.policeName = policePeopleEntity.getPoliceName();
        this.policePhone = policePeopleEntity.getPolicePhone();
        this.unitId = policePeopleEntity.getUnitId();
        this.createTime = new Date();
    }

    @Generated(hash = 521844435)
    public PolicePeople(long id, @NotNull String policeName, @NotNull String policePhone, int unitId,
            @NotNull Date createTime) {
        this.id = id;
        this.policeName = policeName;
        this.policePhone = policePhone;
        this.unitId = unitId;
        this.createTime = createTime;
    }

    @Generated(hash = 1679508681)
    public PolicePeople() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPolicePhone() {
        return policePhone;
    }

    public void setPolicePhone(String policePhone) {
        this.policePhone = policePhone;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }
}
