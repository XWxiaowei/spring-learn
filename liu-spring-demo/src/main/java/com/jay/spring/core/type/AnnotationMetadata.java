package com.jay.spring.core.type;

import com.jay.spring.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public interface AnnotationMetadata extends ClassMetadata {

    Set<String> getAnnotationTypes();


    boolean hasAnnotation(String annotationType);

    public AnnotationAttributes getAnnotationAttributes(String annotationType);
}
