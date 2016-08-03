package com.pb.zk.resource.manager;


import com.pb.util.ObjectAndByteUtil;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 使用 ZooKeeper 统一管理配置文件
 * Created by zhangqiang on 2016/8/2.
 */
public class ZkManager {
    private static Log log = LogFactory.getLog(ZkManager.class);

    public static final String ZK_SERVER = "127.0.0.1:2181";
    public static int connectionTimeout = 50000;
    public static final String ROOT_CONF_NODE = "/config";//配置管理根目录
    public static final String REDIS_NODE = ROOT_CONF_NODE + "/config_redis";

    private static ZkClient zk;

    static {
        zk =  new ZkClient(ZK_SERVER,connectionTimeout);
    }

    /**
     * 插入数据至zookeeper节点
     * @param nodeName
     * @param config
     */
    static void config(String nodeName,Map config){
        if (!zk.exists(nodeName)) {
            zk.createPersistent(nodeName, true);
        }
        zk.writeData(REDIS_NODE, config);
    }


    /**
     * 配置redis
     */
    static void configRedis(){
        Map<String,Object> map = readProps("redis.properties");
        config(REDIS_NODE,map);
    }

    static Map<String,Object> readProps(String prop){
        Map<String,Object> map = new HashMap<>();
        Properties props = new Properties();
        try {
            props.load(ZkManager.class.getClassLoader().getResourceAsStream(prop));
            Set<Object> keySet = props.keySet();
            for(Object key : keySet){
                map.put(key.toString(),props.get(key));
            }
            log.debug(keySet);
        }catch (IOException e){
            log.error("load dubbo.properties error"+e.getMessage());
            e.printStackTrace();
        }

        return map;
    }



    public static void main(String args[]){
        try{
            configRedis();
            System.out.println( zk.getChildren(ROOT_CONF_NODE));
        }finally {
            zk.close();
        }
    }

}
