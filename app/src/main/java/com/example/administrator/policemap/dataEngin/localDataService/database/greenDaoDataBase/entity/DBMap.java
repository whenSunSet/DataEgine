package com.example.administrator.policemap.dataEngin.localDataService.database.greenDaoDataBase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
@Entity
public class DBMap {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private long id;

    @NotNull
    @Property(nameInDb = "key")
    private String key;

    @NotNull
    @Property(nameInDb = "value")
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Generated(hash = 977994144)
    public DBMap(long id, @NotNull String key, @NotNull String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    @Generated(hash = 629229241)
    public DBMap() {
    }

    @Override
    public String toString() {
        return "DBMap{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
