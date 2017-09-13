package com.example.huan.commonandroid.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by huan on 2017/9/12.
 */
@Entity
public class User {
    @Id
    private Long id;
    @Property(nameInDb = "user_name")
    private String username;
    @Transient
    private int tempUsageCount; // not persisted

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 79420138)
    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Generated(hash = 586692638)
    public User() {
    }
}
