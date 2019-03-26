package com.zb.fincore.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/4 0004 18:37
 * 版本: V1.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    /**
     * 当前IOC
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String id, Class<T> type) {
        return applicationContext.getBean(id, type);
    }
}
