package com.example.huan.commonandroid.bean.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/** 报告结果
 * Created by huan on 2017/9/12.
 */
@Entity
public class Result {
    @Property(nameInDb = "item_no")
    private String id;
    @Property(nameInDb = "name_cn")
    private String name;
    @Property(nameInDb = "sample_id")
    private String sampleid;
    @Property(nameInDb = "result")
    private String result;
    @Property(nameInDb = "rank")
    private String rank;
    @Property(nameInDb = "avg_type")
    private String avg_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSampleid() {
        return sampleid;
    }

    public void setSampleid(String sampleid) {
        this.sampleid = sampleid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAvg_type() {
        return avg_type;
    }

    public void setAvg_type(String avg_type) {
        this.avg_type = avg_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Generated(hash = 264011943)
    public Result(String id, String name, String sampleid, String result, String rank, String avg_type) {
        this.id = id;
        this.name = name;
        this.sampleid = sampleid;
        this.result = result;
        this.rank = rank;
        this.avg_type = avg_type;
    }

    @Generated(hash = 1176609929)
    public Result() {
    }
}
