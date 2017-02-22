package com.spike.service.impl;

import com.spike.dao.SpikeDao;
import com.spike.dao.SuccessSpikeDao;
import com.spike.dto.Exposer;
import com.spike.dto.SpikeExecution;
import com.spike.enums.StateEnum;
import com.spike.exception.RepeatSpikeException;
import com.spike.exception.SpikeClosedException;
import com.spike.exception.SpikeException;
import com.spike.pojo.Spike;
import com.spike.pojo.SuccessSpike;
import com.spike.service.SpikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * SpikeService接口实现
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */

@Service
public class SpikeServiceImpl implements SpikeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpikeDao spikeDao;

    @Autowired
    private SuccessSpikeDao successSpikeDao;

    private final String salt = "LJSDLJLS#$%^)(*sjdfJSD53sfds32d1f5sad1f3";//混淆盐值


    public List<Spike> getSpikeList() {
        return spikeDao.queryAll(0, 5);
    }

    public Spike getSpikeById(long spikeId) {
        return spikeDao.queryById(spikeId);
    }

    public Exposer exportSpikeUrl(long spikeId) {
        Spike  spike = spikeDao.queryById(spikeId);
        if (spike == null) {
            return new Exposer(false, spikeId);
        }
        Date startTime = spike.getStartTime();
        Date endTime = spike.getEndTime();
        Date now = new Date();
        if (startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()) {
            return new Exposer(false, spikeId, now.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = md5(spikeId);

        return new Exposer(true, md5, spikeId);
    }

    /**
     *使用基于注解的声明式事务能够提醒开发人员注意。
     *在事务方法中不要穿插网络请求RPC，HTTP等。
     *不是所有的方法都需要事务（只有一条修改操作，只读操作不需要事务控制）MySQL行级锁
     */
    @Transactional
    public SpikeExecution executeSpike(long spikeId, String userPhone, String md5) throws RepeatSpikeException, SpikeClosedException, SpikeException {

        try {
            if (md5 == null || ! md5.equals(md5(spikeId))) {
                throw new SpikeException("The url data was overwritten.");
            }

            int updateCount = spikeDao.reduceNumber(spikeId, new Date()); //减库存
            if (updateCount <= 0) {
                throw new SpikeClosedException("Spike was closed.");
            }
            else {
                int insertCount = successSpikeDao.insertSuccessSpike(spikeId, userPhone); //添加秒杀成功明细
                if (insertCount <= 0) {
                    throw new RepeatSpikeException("Spike was repeated.");
                }
                else {
                    SuccessSpike successSpike = successSpikeDao.queryById(spikeId);
                    return new SpikeExecution(spikeId, StateEnum.SUCCESS, successSpike);
                }
            }
        } catch (SpikeClosedException sce) {
            logger.error(sce.getMessage(), sce);
            throw sce;
        } catch (RepeatSpikeException rse) {
            logger.error(rse.getMessage(), rse);
            throw rse;
        } catch (SpikeException se) {
            logger.error(se.getMessage(), se);
            throw se;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SpikeException("Inner error" + e.getMessage()); //全部转化为运行期异常。
        }

    }

    private String md5(long spikeId) {
        String base = spikeId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
