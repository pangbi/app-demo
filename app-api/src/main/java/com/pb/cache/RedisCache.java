package com.pb.cache;

import com.pb.helper.JedisHandler;
import com.pb.helper.JedisManager;
import com.pb.helper.SpringBeanHelper;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhangqiang on 2016/7/27.
 */
public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private static final String rootKey = "mybatis_cache_key_";
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private JedisManager jedisManager;

    private String id;
    public RedisCache(final String id) {
        logger.debug("cache id===>" + id);
        this.id = id;
        jedisManager = (JedisManager)SpringBeanHelper.getBean("jedisManager");
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return jedisManager.execute(new JedisHandler<Integer>() {
            @Override
            public Integer handle(Jedis jedis) {
                return Integer.valueOf(jedis.dbSize().toString());
            }
        });
    }

    public void putObject(Object key, Object value) {
        logger.debug("set key ===============>"+rootKey+"*");
        jedisManager.set(rootKey+key.toString(),value);

    }

    public Object getObject(Object key) {
        logger.debug("get key ===============>"+rootKey+"*");
        return jedisManager.get(rootKey+key.toString());
    }

    public Object removeObject(Object key) {
        logger.debug("remove keys ===============>"+rootKey+"*");
        return jedisManager.delete(rootKey+key.toString());
    }

    public void clear() {
         jedisManager.execute(new JedisHandler<Object>() {
            @Override
            public Object handle(Jedis jedis) {
                // TODO: 2016/8/4 后期可以通过lua脚本执行批量删除指定key的操作 
                logger.debug("clear keys ===============>"+rootKey+"*");
                Set<String> keySets = jedis.keys(rootKey +"*");
                for(String key : keySets){
                    jedis.del(key);
                }
                return null;
            }
        });
    }


    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
