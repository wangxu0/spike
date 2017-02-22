package com.spike.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * SpikeDao接口测试。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SpikeDaoTest {


    @Resource
    private SpikeDao spikeDao;

    @Test
    public void reduceNumber() throws Exception {
        spikeDao.reduceNumber(1000, new Date());
    }

    @Test
    public void queryById() throws Exception {
        System.out.println(spikeDao.queryById(1000));
    }

    @Test
    public void queryAll() throws Exception {
        System.out.println(spikeDao.queryAll(0,1000));
    }

}