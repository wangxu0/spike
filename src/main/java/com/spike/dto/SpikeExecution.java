package com.spike.dto;

import com.spike.enums.StateEnum;
import com.spike.pojo.SuccessSpike;

/**
 * 存放秒杀执行后的结果DTO
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class SpikeExecution {

    private long spikeId;

    private int state;

    private String stateInfo;

    private SuccessSpike successSpike;

    public SpikeExecution(long spikeId, StateEnum stateEnum, SuccessSpike successSpike) {
        this.spikeId = spikeId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successSpike = successSpike;
    }

    public SpikeExecution(long spikeId, StateEnum stateEnum) {
        this.spikeId = spikeId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSpikeId() {
        return spikeId;
    }

    public void setSpikeId(long spikeId) {
        this.spikeId = spikeId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
