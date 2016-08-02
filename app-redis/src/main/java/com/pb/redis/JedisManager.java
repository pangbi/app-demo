package com.pb.redis;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhangqiang on 2016/7/26.
 */
@Component
public class JedisManager {
    Log log = LogFactory.getLog(JedisManager.class);
    @Autowired
    private JedisPool jedisPool;
    public <T> T execute(JedisHandler<T> handler) {
        Jedis jedis = jedisPool.getResource();
        T t = null;
        try {
            log.error("begin redis execute");
            t = (T) handler.handle(jedis);
        } catch (Exception e) {
            log.error("redis execute error"+e.getMessage());
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
        log.error("end redis execute");
        return t;
    }
}
