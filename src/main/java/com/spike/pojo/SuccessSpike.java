package com.spike.pojo;

import java.util.Date;

/**
 * 实体类，秒杀成功明细。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class SuccessSpike {

    private long spikeId;
    private String userPhone;
    private short state;
    private Date createTime;

    private Spike spike; //秒杀成功明细和商品之间是多对一的关系，一个商品可能被多个用户秒杀。

    public long getSpikeId() {
        return spikeId;
    }

    public void setSpikeId(long spikeId) {
        this.spikeId = spikeId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Spike getSpike() {
        return spike;
    }

    public void setSpike(Spike spike) {
        this.spike = spike;
    }

    @Override
    public String toString() {
        return "SuccessSpike{" +
                "spikeId=" + spikeId +
                ", userPhone='" + userPhone + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
