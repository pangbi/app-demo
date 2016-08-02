package com.pb.redis;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.pb.facde.RedisFacde;
import com.pb.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


/**
 * Created by zhangqiang on 2016/7/26.
 */
@Service(protocol = {"dubbo"})
public class RedisFacdeImpl implements RedisFacde {
    Log log = LogFactory.getLog(RedisFacdeImpl.class);

    @Autowired
    JedisManager jedisManager;

    public boolean set(String key, Object value) {
        return jedisManager.execute(new JedisHandler<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) {
                jedis.set(key.getBytes(), SerializeUtil.serialize(value));
                return true;
            }
        });
    }

    public <T> T get(String key) {
        return jedisManager.execute(new JedisHandler<T>() {
            @Override
            public T handle(Jedis jedis) {
                byte[] b = jedis.get(key.getBytes());
                return (T)SerializeUtil.unserialize(b);
            }
        });
    }

    public boolean delete(String key) {
        return jedisManager.execute(new JedisHandler<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) {
                jedis.del(key);
                return true;
            }
        });
    }

}
