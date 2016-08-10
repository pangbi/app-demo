package com.pb.zk.resource.manager;


import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger log = LoggerFactory.getLogger(ZkManager.class);

    public static final String ZK_SERVER = "127.0.0.1:2181";
    public static int connectionTimeout = 50000;
    public static final String ROOT_CONF_NODE = "/config";//配置管理根目录
    public static final String REDIS_NODE = ROOT_CONF_NODE + "/config_redis";//redis配置节点
    public static final String MYSQL_NODE = ROOT_CONF_NODE + "/config_mysql";//redis配置节点

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
        zk.writeData(nodeName, config);
    }


    /**
     * 资源读取
     * @param prop
     * @return
     */
    static Map<String,Object> readProps(String prop){
        Map<String,Object> map = new HashMap<String,Object>();
        Properties props = new Properties();
        try {
            props.load(ZkManager.class.getClassLoader().getResourceAsStream(prop));
            Set<Object> keySet = props.keySet();
            for(Object key : keySet){
                map.put(key.toString(),props.get(key));
            }
            log.debug("properties : "+keySet.toString());
        }catch (IOException e){
            log.error("load dubbo.properties error"+e.getMessage());
            e.printStackTrace();
        }

        return map;
    }


    /**
     * 配置redis
     */
    static void configRedis(){
        Map<String,Object> map = readProps("redis.properties");
        config(REDIS_NODE,map);
    }

    /**
     * 配置db
     */
    static void configMysql(){
        Map<String,Object> map = readProps("db.properties");
        config(MYSQL_NODE,map);
    }

    /**
     * 配置日志级别
     */
    static void configLogLevel(){
        Map<String,Object> map = readProps("log.properties");
        if(map != null ){
            Set<String> keySets = map.keySet();
            for(String key : keySets){
                config(key,map);
            }
        }
    }

    public static void main(String args[]){
        try{
            configRedis();
            configMysql();
            configLogLevel();
            /*
            for(String node : zk.getChildren(ROOT_CONF_NODE)){
                zk.delete(ROOT_CONF_NODE+"/"+node);
            }
            */
            log.debug("zk nodes :" + zk.getChildren(ROOT_CONF_NODE).toString());
        }finally {
            zk.close();
        }
    }

}
