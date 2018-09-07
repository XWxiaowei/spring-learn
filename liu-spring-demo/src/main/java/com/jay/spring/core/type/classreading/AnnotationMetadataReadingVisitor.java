package com.jay.spring.core.type.classreading;

import com.jay.spring.core.annotation.AnnotationAttributes;
import com.jay.spring.core.type.AnnotationMetadata;
import jdk.internal.org.objectweb.asm.Type;
import org.springframework.asm.AnnotationVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 * implements AnnotationMetadata
 */
public class AnnotationMetadataReadingVisitor extends  ClassMetadataReadingVisitor implements AnnotationMetadata {
    private final Set<String> annotationSet = new LinkedHashSet<String>(4);
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<String, AnnotationAttributes>(4);

    public AnnotationMetadataReadingVisitor() {

    }
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
    }
    @Override
    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    @Override
    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    @Override
    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }

    @Override
    public boolean hasSuperClass() {
        return false;
    }
}
