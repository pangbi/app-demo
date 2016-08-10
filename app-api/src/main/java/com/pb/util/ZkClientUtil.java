package com.pb.util;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangqiang on 2016/8/3.
 */
public class ZkClientUtil {
    static Logger log = LoggerFactory.getLogger(ZkClientUtil.class);
    //zookeeper地址
    private static String servers;
    //链接超时时间
    private static int connectionTimeout = 50000;

    private static ZkClient zk;

    private static String mainPath;

    public ZkClientUtil(){
        Properties props = new Properties();
        try {
            props.load(ZkClientUtil.class.getClassLoader().getResourceAsStream("dubbo.properties"));
            servers = props.getProperty("dubbo.registry.address");
            mainPath = props.getProperty("dubbo.config.mainPath");

        }catch (IOException e){
            log.error("load dubbo.properties error"+e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 获取节点数据
     * @return
     */
    public static Map<String,Object> getData(){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            zk = new ZkClient(servers,connectionTimeout);
            List<String> childs =  zk.getChildren(mainPath);
            if(childs != null && childs.size() > 0){
                for(String node : childs){
                    if(node != null && node.indexOf("config_") != -1){
                        Map dataMap =  zk.readData(mainPath + "/" + node);
                        if(dataMap !=null && dataMap.size() > 0)
                            map.putAll(dataMap);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(zk != null)
                zk.close();
        }


        return map;
    }
}
