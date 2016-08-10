package com.pb.logHelper;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/8/4.
 * 监听zk node 变化
 */
public class AppLogConfig implements IZkDataListener{

    public static Map<String,String> MAP = new HashMap<String, String>();
    private static final Logger logger = LoggerFactory.getLogger(AppLogConfig.class);

    /**
     * 监听zk node节点数据变化
     * @param dataPath
     * @param data
     * @throws Exception
     */
    public void handleDataChange(String dataPath, Object data) throws Exception {
        logger.debug("handleDataChange=====>dataPath:"+dataPath+",data:"+data);
        String[] tmp = dataPath.split("/");
        //app name is at the end of the path  example:/config/logConfig/app-redis
        String appName = tmp[tmp.length-1];
        Object value = ((Map)data).get(dataPath);
        AppLogConfig.MAP.put(appName, value==null?"DEBUG":(String)value);
    }

    public void handleDataDeleted(String s) throws Exception {

    }
}
