package com.pb.config;

import java.util.List;
import java.util.Properties;

import com.alibaba.dubbo.common.utils.StringUtils;
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
    private ZookeeperClientUtil configurationClient;

    public void setConfigurationClient(ZookeeperClientUtil configurationClient) {
        this.configurationClient = configurationClient;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        log.debug("**************************processProperties************************");
        try {
            List<String> list = configurationClient.getChildren();
            System.err.println(list);
            for (String key : list) {
                String value = configurationClient.getData(configurationClient.getMainPath() + "/" + key);
                System.err.println("prop value =======>"+value);
                if (!StringUtils.isBlank(value)) {
                    props.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.processProperties(beanFactoryToProcess, props);

    }
}