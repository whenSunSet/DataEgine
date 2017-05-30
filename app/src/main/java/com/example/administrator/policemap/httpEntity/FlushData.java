package com.example.administrator.policemap.httpEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class FlushData {

    private FlushDataBean flushData;

    public FlushData() {
    }

    public FlushData(FlushDataBean flushData) {
        this.flushData = flushData;
    }

    public FlushDataBean getFlushData() {
        return flushData;
    }

    public void setFlushData(FlushDataBean flushData) {
        this.flushData = flushData;
    }

    public static class FlushDataBean{
        private ArrayList<MissionInfoEntity> infoEntityArrayList =new ArrayList<>();
        private ArrayList<PolicePeopleEntity> peopleArrayList =new ArrayList<>();
        private ArrayList<UnitBaseEntity> unitBaseArrayList =new ArrayList<>();

        public FlushDataBean(ArrayList<MissionInfoEntity> infoEntityArrayList, ArrayList<PolicePeopleEntity> peopleArrayList, ArrayList<UnitBaseEntity> unitBaseArrayList) {
            this.infoEntityArrayList = infoEntityArrayList;
            this.peopleArrayList = peopleArrayList;
            this.unitBaseArrayList = unitBaseArrayList;
        }

        public ArrayList<MissionInfoEntity> getInfoEntityArrayList() {
            return infoEntityArrayList;
        }

        public void setInfoEntityArrayList(ArrayList<MissionInfoEntity> infoEntityArrayList) {
            this.infoEntityArrayList = infoEntityArrayList;
        }

        public ArrayList<PolicePeopleEntity> getPeopleArrayList() {
            return peopleArrayList;
        }

        public void setPeopleArrayList(ArrayList<PolicePeopleEntity> peopleArrayList) {
            this.peopleArrayList = peopleArrayList;
        }

        public ArrayList<UnitBaseEntity> getUnitBaseArrayList() {
            return unitBaseArrayList;
        }

        public void setUnitBaseArrayList(ArrayList<UnitBaseEntity> unitBaseArrayList) {
            this.unitBaseArrayList = unitBaseArrayList;
        }

        @Override
        public String toString() {
            return "FlushData{" +
                    "infoEntityArrayList=" + infoEntityArrayList +
                    ", peopleArrayList=" + peopleArrayList +
                    ", unitBaseArrayList=" + unitBaseArrayList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FlushData{" +
                "flushData=" + flushData +
                '}';
    }
}
