package com.pb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Map;
import java.util.Properties;

/**
 * Created by zhangqiang on 2016/7/26.
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    Logger log = LoggerFactory.getLogger(ZooKeeperPropertyPlaceholderConfigurer.class);
    private ZkClientUtil zkClientUtil;

    public void setZkClientUtil(ZkClientUtil zkClientUtil) {
        log.debug("init ZooKeeperPropertyPlaceholderConfigurer");
        this.zkClientUtil = zkClientUtil;
    }

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