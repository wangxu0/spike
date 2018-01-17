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

    private final String password;

    public RedisDao(String ip, int port, String password) {
        this.jedisPool = new JedisPool(ip, port);
        this.password = password;
    }

    private Jedis getResource() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(password);
        return jedis;
    }

    private RuntimeSchema<Spike> runtimeSchema = RuntimeSchema.createFrom(Spike.class);

    public Spike getSpike(long spikeId) {
        try {
            Jedis jedis = getResource();
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
            Jedis jedis = getResource();
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
