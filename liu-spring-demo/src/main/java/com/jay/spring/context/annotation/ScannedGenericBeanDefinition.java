package com.jay.spring.context.annotation;

import com.jay.spring.bean.factory.annotation.AnnotatedBeanDefinition;
import com.jay.spring.bean.factory.support.GenericBeanDefinition;
import com.jay.spring.core.type.AnnotationMetadata;

/**
 * Created by xiang.wei on 2018/7/15
 *
 * @author xiang.wei
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {
    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();

        this.metadata = metadata;
        setBeanClassName(this.metadata.getClassName());

    }

    @Override
    public AnnotationMetadata getMetadata() {
        return this.metadata;
    }

}
