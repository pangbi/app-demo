package com.pb.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    Log log = LogFactory.getLog(ZooKeeperPropertyPlaceholderConfigurer.class);
    //private ZkClientUtil zkClientUtil;

   /* public void setZkClientUtil(ZkClientUtil zkClientUtil) {
        log.debug("init ZooKeeperPropertyPlaceholderConfigurer");
        this.zkClientUtil = zkClientUtil;
    }*/

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        log.debug("**************************processProperties*************************");
        try {

            Map<String, Object> configMap = ZkClientUtil.getData();
            log.debug("prop value =======>" + configMap);
            if (configMap != null && configMap.size() > 0) {
                for (String k : configMap.keySet()) {
                    log.debug("prop key :" + k + " value :" + configMap.get(k));
                    props.put(k, configMap.get(k));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.processProperties(beanFactoryToProcess, props);

    }
}