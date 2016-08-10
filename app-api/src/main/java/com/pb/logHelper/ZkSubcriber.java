package com.pb.logHelper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangqiang on 2016/8/4.
 */
public class ZkSubcriber {
    private static final Logger logger = LoggerFactory.getLogger(ZkSubcriber.class);

    private ZkClient zk;
    private static int connectionTimeout = 50000;

    public ZkSubcriber(String zkAddress, String logPath, IZkDataListener listener){
        zk = new ZkClient(zkAddress,connectionTimeout);
        this.checkNode(logPath);
        zk.subscribeDataChanges(logPath,listener);
        logger.debug("ZK listener subcribed", zkAddress);
    }

    private void checkNode(String path){
        boolean exist = zk.exists(path);
        if(!exist){
            zk.createPersistent(path, true);
            logger.debug("ZK node {} created", path);
        }
    }


}
