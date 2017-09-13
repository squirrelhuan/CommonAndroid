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
public class Sample {
    @Id
    private Long id;
    @Property(nameInDb = "sample_name")
    private String samplename;
    @Transient
    private int tempUsageCount; // not persisted

    public String getSamplename() {
        return samplename;
    }

    public void setSamplename(String samplename) {
        this.samplename = samplename;
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

    @Generated(hash = 925861570)
    public Sample(Long id, String samplename) {
        this.id = id;
        this.samplename = samplename;
    }

    @Generated(hash = 976859954)
    public Sample() {
    }
}
