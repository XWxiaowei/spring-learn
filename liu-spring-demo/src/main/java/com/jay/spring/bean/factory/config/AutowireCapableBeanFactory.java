package com.jay.spring.bean.factory.config;

import com.jay.spring.bean.factory.BeanFactory;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     *
     * @param dependencyDescriptor
     * @return
     */
    Object resolveDependency(DependencyDescriptor dependencyDescriptor);


}
