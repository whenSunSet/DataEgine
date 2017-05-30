package com.example.administrator.policemap.httpEntity;

/**
 * Created by heshixiyang on 2016/12/4.
 */
public class PolicePeopleEntity {
    private int id;
    private String policeName;
    private String policePhone;
    private int unitId;
    private String createTime;

    public PolicePeopleEntity() {
    }

    public PolicePeopleEntity(int id, String policeName, String policePhone, int unitId, String createTime) {
        this.id = id;
        this.policeName = policeName;
        this.policePhone = policePhone;
        this.unitId = unitId;
        this.createTime = createTime;
    }

    public int getId() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PolicePeopleEntity{" +
                "id=" + id +
                ", policeName='" + policeName + '\'' +
                ", policePhone='" + policePhone + '\'' +
                ", unitId=" + unitId +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
