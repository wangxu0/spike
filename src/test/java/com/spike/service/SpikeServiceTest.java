package com.spike.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * SpikeService接口测试
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SpikeServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpikeService spikeService;

    @Test
    public void getSpikeList() throws Exception {
        logger.info("list={}", spikeService.getSpikeList());
    }

    @Test
    public void getSpikeById() throws Exception {
        logger.info("spike={}", spikeService.getSpikeById(1000));
    }

    @Test
    public void exportSpikeUrl() throws Exception {
        logger.info("exposer={}", spikeService.exportSpikeUrl(1000));
    }

    @Test
    public void executeSpike() throws Exception {
        String md5 = "89ea9112d012b63cc813b3593180a69e";
        spikeService.executeSpike(1000, "13515425332", md5);
    }

}