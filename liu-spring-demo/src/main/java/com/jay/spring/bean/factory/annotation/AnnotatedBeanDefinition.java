package com.jay.spring.bean.factory.annotation;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.core.type.AnnotationMetadata;

/**
 * Created by xiang.wei on 2018/7/15
 *
 * @author xiang.wei
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getMetadata();
}
