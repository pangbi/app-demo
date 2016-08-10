package com.pb.helper;

import com.pb.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public class JedisManager {
    Logger log = LoggerFactory.getLogger(JedisManager.class);
    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T execute(JedisHandler<T> handler) {
        Jedis jedis = jedisPool.getResource();
        T t = null;
        try {
            log.debug("begin redis execute");
            t = (T) handler.handle(jedis);
        } catch (Exception e) {
            log.debug("redis execute error"+e.getMessage());
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
        log.debug("end redis execute");
        return t;
    }

    public boolean set(final String key,final  Object value) {
        return execute(new JedisHandler<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) {
                jedis.set(key.getBytes(), SerializeUtil.serialize(value));
                return true;
            }
        });
    }

    public <T> T get(final String key) {
        return execute(new JedisHandler<T>() {
            @Override
            public T handle(Jedis jedis) {
                byte[] b = jedis.get(key.getBytes());
                return b==null ? null : (T)SerializeUtil.unserialize(b);
            }
        });
    }

    public boolean delete(final String key) {
        return execute(new JedisHandler<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) {
                jedis.del(key);
                return true;
            }
        });
    }
}
