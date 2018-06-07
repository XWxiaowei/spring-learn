package com.jay.spring.beans;

/**
 * @author xiang.wei
 * @create 2018/6/7 19:09
 */
public interface BeanPostProcessor {
    /**
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;

}
