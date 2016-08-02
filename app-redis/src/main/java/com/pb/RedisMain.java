package com.pb;

import com.pb.redis.RedisFacdeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public class RedisMain {
    public static void main(String args[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");
        com.alibaba.dubbo.container.Main.main(null);
        /*RedisFacdeImpl redisFacdeImpl = (RedisFacdeImpl)ac.getBean("redisFacdeImpl");
        Jedis jedis = redisFacdeImpl.getJedis();
        System.out.println("--->");
        jedis.set("pb","张强");
        System.out.println(jedis.get("pb"));*/
    }
}
