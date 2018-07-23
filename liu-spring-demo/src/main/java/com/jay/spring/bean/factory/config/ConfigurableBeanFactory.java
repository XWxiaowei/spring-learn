package com.jay.spring.bean.factory.config;

import java.util.List;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public interface ConfigurableBeanFactory  extends AutowireCapableBeanFactory{

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
    void addBeanPostProcessor(BeanPostProcessor postProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();


}
