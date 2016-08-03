package com.pb.zk.resource.manager;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by zhangqiang on 2016/8/3.
 */
public class ZKListener {
    public static final String ZK_SERVER = "127.0.0.1:2181";
    public static int connectionTimeout = 50000;
    public static final String ROOT_CONF_NODE = "/config";//配置管理根目录
    public static final String REDIS_NODE = ROOT_CONF_NODE + "/redis_config";
    private static ZkClient zk;

    static {
        zk = new ZkClient(ZK_SERVER, connectionTimeout);
    }

    public static void main(String args[]) {
        zk.subscribeChildChanges(ROOT_CONF_NODE, new IZkChildListener() {
            @Override
            public void handleChildChange(String root, List<String> list) throws Exception {
                System.out.println("=======subscribeChildChanges=====");
                System.out.println(root);
                System.out.println(list);
            }
        });



        zk.subscribeDataChanges(REDIS_NODE, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("=======subscribeDataChanges=====");
                System.out.println(s);
                System.out.println(o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });


        while (1==1){}
    }
}
