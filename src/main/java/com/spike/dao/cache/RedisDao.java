package com.spike.dao.cache;


import com.spike.pojo.Spike;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Spike> runtimeSchema = RuntimeSchema.createFrom(Spike.class);

    public Spike getSpike(long spikeId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "spike:"+spikeId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Spike spike = runtimeSchema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, spike, runtimeSchema);
                    return spike;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSpike(Spike spike) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "spike:" + spike.getSpikeId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(spike, runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                return jedis.setex(key.getBytes(), 3600, bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
