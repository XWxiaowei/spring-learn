package com.jay.spring.bean.factory.config;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public interface ConfigurableBeanFactory  extends AutowireCapableBeanFactory{

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
