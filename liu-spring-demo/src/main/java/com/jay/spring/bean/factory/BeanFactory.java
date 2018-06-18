package com.jay.spring.bean.factory;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:36
 */
public interface BeanFactory {

    /**
     * 获取service的实例
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
    Object getBean(String id);


}
