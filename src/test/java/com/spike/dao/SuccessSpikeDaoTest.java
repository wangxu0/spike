package com.spike.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * SuccessSpikeDao接口测试。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessSpikeDaoTest {

    @Resource
    private SuccessSpikeDao successSpikeDao;


    @Test
    public void insertSuccessSpike() throws Exception {
        successSpikeDao.insertSuccessSpike(1000L, "13555667788");
    }

    @Test
    public void queryById() throws Exception {
        System.out.println(successSpikeDao.queryById(1000));
    }

}