package com.pb.config;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhangqiang on 2016/8/3.
 */
public class ZkClientUtil {
    static Log log = LogFactory.getLog(ZkClientUtil.class);
    //zookeeper地址
    private static String servers;
    //链接超时时间
    private static int connectionTimeout = 50000;

    private static ZkClient zk;

    private static String mainPath;

    static {
        Properties props = new Properties();
        try {
            props.load(ZkClientUtil.class.getClassLoader().getResourceAsStream("dubbo.properties"));
            servers = props.getProperty("dubbo.registry.address");
            mainPath = props.getProperty("dubbo.config.mainPath");
            zk = new ZkClient(servers,connectionTimeout);

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
            zk.close();
        }


        return map;
    }
}
