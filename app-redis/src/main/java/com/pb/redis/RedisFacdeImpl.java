package com.pb.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.pb.facde.RedisFacde;
import com.pb.helper.JedisHandler;
import com.pb.helper.JedisManager;
import com.pb.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


/**
 * Created by zhangqiang on 2016/7/26.
 */
@Service(protocol = {"dubbo"})
public class RedisFacdeImpl implements RedisFacde {
    Logger log = LoggerFactory.getLogger(RedisFacdeImpl.class);

    @Autowired
    JedisManager jedisManager;

    public boolean set(String key, Object value) {
        return jedisManager.set(key,value);
    }

    public <T> T get(String key) {
        return jedisManager.get(key);
    }

    public boolean delete(String key) {
        return jedisManager.delete(key);
    }

}
