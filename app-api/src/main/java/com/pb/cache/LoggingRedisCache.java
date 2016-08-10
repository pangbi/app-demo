package com.pb.cache;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * Created by zhangqiang on 2016/7/27.
 */
public class LoggingRedisCache extends LoggingCache {
    public LoggingRedisCache(String id) {
        super(new RedisCache(id));
    }
}
