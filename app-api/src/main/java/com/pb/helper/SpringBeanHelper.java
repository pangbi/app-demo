package com.pb.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by zhangqiang on 2016/7/27.
 */
public class SpringBeanHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    public SpringBeanHelper() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanId) {
        return context.getBean(beanId);
    }
}
