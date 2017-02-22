package com.spike.dto;

/**
 * 暴露秒杀接口地址DTO
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class Exposer {

    //是否暴露
    private boolean isExposed;

    //加密值
    private String md5;

    //系统当前时间
    private long now;

    //开始时间
    private long start;

    //结束时间
    private long end;

    //对应的库存id
    private long spikeId;

    public Exposer(boolean isExposed, long now, long start, long end) {
        this.isExposed = isExposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean isExposed, long spikeId) {
        this.isExposed = isExposed;
        this.spikeId = spikeId;
    }

    public Exposer(boolean isExposed, long spikeId, long now, long start, long end) {
        this.isExposed = isExposed;
        this.now = now;
        this.start = start;
        this.end = end;
        this.spikeId = spikeId;
    }

    public Exposer(boolean isExposed, String md5, long spikeId) {
        this.isExposed = isExposed;
        this.md5 = md5;
        this.spikeId = spikeId;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getSpikeId() {
        return spikeId;
    }

    public void setSpikeId(long spikeId) {
        this.spikeId = spikeId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "isExposed=" + isExposed +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                ", spikeId=" + spikeId +
                '}';
    }
}
