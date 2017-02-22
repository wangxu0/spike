package com.spike.pojo;

import java.util.Date;

/**
 * 实体类，秒杀库存。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class Spike {

    private long spikeId;
    private String name;
    private int nubmer;
    private Date startTime;
    private Date endTime;
    private Date createTime;

    public long getSpikeId() {
        return spikeId;
    }

    public void setSpikeId(long spikeId) {
        this.spikeId = spikeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNubmer() {
        return nubmer;
    }

    public void setNubmer(int nubmer) {
        this.nubmer = nubmer;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Spike{" +
                "spikeId=" + spikeId +
                ", name='" + name + '\'' +
                ", nubmer=" + nubmer +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
