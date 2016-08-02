package com.pb.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public class ZookeeperClientUtil {
    Log log = LogFactory.getLog(ZookeeperClientUtil.class);
    private ZooKeeper zk;
    //zookeeper地址
    private String servers;
    //链接超时时间
    private int sessionTimeout = 40000;

    private String mainPath;

    public ZookeeperClientUtil(){
        Properties props = new Properties();
        try {
            props.load(ZookeeperClientUtil.class.getClassLoader().getResourceAsStream("dubbo.properties"));
            servers = props.getProperty("dubbo.registry.address");
            mainPath = props.getProperty("dubbo.config.mainPath");
        }catch (IOException e){
            log.error("load dubbo.properties error"+e.getMessage());
            e.printStackTrace();
        }

    }

    public ZooKeeper getAliveZk() {
        ZooKeeper aliveZk = zk;
        if (aliveZk != null && aliveZk.getState().isAlive()) {
            return aliveZk;
        } else {
            zkReconnect();
            return zk;
        }
    }
    public synchronized void zkReconnect() {
        close();
        try {
            connect();
        } catch (IOException e) {
        }
    }
    public synchronized void close() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
            }
            zk = null;
        }
    }
    private synchronized void connect() throws IOException {
        if (zk == null  && !StringUtils.isBlank(servers))
            zk = new ZooKeeper(servers, sessionTimeout, null);
    }
    public String getData(String path) {
        String result = null;
        try {
            byte [] data = getAliveZk().getData(path, Boolean.TRUE,null);
            if(null != data){
                result = new String(data, "UTF-8");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result ;
    }
    public List<String> getChildren(){
        List<String> data = null;
        try {
            data = getAliveZk().getChildren(mainPath, Boolean.TRUE);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
    public String getMainPath() {
        return mainPath;
    }
    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }
    public void setServers(String servers) {
        this.servers = servers;
    }
}
