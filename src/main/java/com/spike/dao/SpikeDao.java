package com.spike.dao;

import com.spike.pojo.Spike;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 商品库存DAO接口。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public interface SpikeDao {

    /**
     * 库存减一
     * @param spikeId 对应的商品id
     * @param killTime 秒杀时间
     * @return 更新行数
     */
    public int reduceNumber(@Param("spikeId") long spikeId, @Param("killTime") Date killTime);

    /**
     * 根据商品id查询商品
     * @param spikeId 对应的商品id
     * @return 查询到的商品库存信息
     */
    public Spike queryById(long spikeId);

    /**
     * 查询偏移量的商品列表
     * @param offset 偏移量
     * @param limit 商品数量限制
     * @return 库存列表
     */
    public List<Spike> queryAll(@Param("offset") int offset, @Param("limit") int limit);



}
