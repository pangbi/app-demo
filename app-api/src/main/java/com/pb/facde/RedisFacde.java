package com.pb.facde;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public interface RedisFacde {
    boolean set(String key,Object value);
    <T> T get(String key);
    boolean delete(String key);
}
