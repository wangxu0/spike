package com.spike.dao;

import com.spike.pojo.SuccessSpike;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 秒杀成功明细DAO接口
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public interface SuccessSpikeDao {

    /**
     * 插入秒杀成功明细并去重
     * @param spikeId 商品id
     * @param userPhone 用户手机号
     * @return 插入的行数
     */
    public int insertSuccessSpike(@Param("spikeId") long spikeId, @Param("userPhone") String userPhone);


    /**
     * 根据商品id查询明细
     * @param spikeId 商品id
     * @return 商品秒杀明细
     */
    public SuccessSpike queryById(long spikeId);

    /**
     * 通过存储过程秒杀
     * @param params 存储过程参数，包括返回值
     */
    public void executeSpikeByProcedure(Map<String, Object> params);



}
