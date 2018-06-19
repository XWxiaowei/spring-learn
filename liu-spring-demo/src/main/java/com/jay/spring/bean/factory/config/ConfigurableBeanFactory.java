package com.jay.spring.bean.factory.config;

import com.jay.spring.bean.factory.BeanFactory;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public interface ConfigurableBeanFactory  extends BeanFactory{

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
