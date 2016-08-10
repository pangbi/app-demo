package com.pb.helper;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public  abstract class JedisHandler<T> {
    /**
     * 业务具体实现
     * @param jedis
     * @return
     */
    public abstract T handle(Jedis jedis);
}