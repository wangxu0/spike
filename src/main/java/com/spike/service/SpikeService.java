package com.spike.service;

import com.spike.dto.Exposer;
import com.spike.dto.SpikeExecution;
import com.spike.exception.RepeatSpikeException;
import com.spike.exception.SpikeClosedException;
import com.spike.exception.SpikeException;
import com.spike.pojo.Spike;

import java.util.List;

/**
 * 秒杀service接口
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public interface SpikeService {

    /**
     * 查询所有的秒杀库存
     * @return 库存列表
     */
    public List<Spike> getSpikeList();

    /**
     * 根据id查询库存
     * @param spikeId
     * @return 库存
     */
    public Spike getSpikeById(long spikeId);

    /**
     * 秒杀接口暴露，防止程序自动秒杀。
     * @param spikeId
     * @return Exposer
     */
    public Exposer exportSpikeUrl(long spikeId);

    /**
     * 执行秒杀
     * @param spikeId 库存id
     * @param userPhone 用户手机号
     * @param md5 md5值 (如果md5值改变说明用户的秒杀地址被篡改)
     */
    public SpikeExecution executeSpike(long spikeId, String userPhone, String md5) throws RepeatSpikeException,SpikeClosedException,SpikeException;

    public SpikeExecution executeSpikeByProcedure(long spikeId, String userPhone, String md5) throws RepeatSpikeException, SpikeClosedException, SpikeException;
}
