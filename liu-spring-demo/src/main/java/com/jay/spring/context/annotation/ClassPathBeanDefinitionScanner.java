package com.jay.spring.context.annotation;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.BeanDefinitionStoreException;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;
import com.jay.spring.bean.factory.support.BeanNameGenerator;
import com.jay.spring.core.io.Resource;
import com.jay.spring.core.io.support.PackageResourceLoader;
import com.jay.spring.core.type.classreading.MetadataReader;
import com.jay.spring.core.type.classreading.SimpleMetadataReader;
import com.jay.spring.stereotype.Component;
import com.jay.spring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1. 给定一个package的名称列表，例如
 org.litespring.service.v4,org.litespring.dao.v4"

 2. 对指定的package 进行扫描(scan)，找到那些标记为@Component 的类，创建ScannedGenericBeanDefinition，并且注册到BeanFactory中。

 * Created by xiang.wei on 2018/7/15
 *
 * @author xiang.wei
 */
public class ClassPathBeanDefinitionScanner {
    private final BeanDefinitionRegistry registry;

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private final Log logger = LogFactory.getLog(getClass());

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     *
     * @param packagesToScan 传入的base-package 字符串
     * @return
     */
    public Set<BeanDefinition> doScan(String packagesToScan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
//      对每一个basePackages进行循环
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getID(), candidate);
            }
        }
        return beanDefinitions;
    }

    /**
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {
//            先拿到Resource
            Resource[] resources = this.resourceLoader.getResources(basePackage);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    //  通过metadataReader 获取AnnotationMetadata 看看其是否有Component注解
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
//实例化一个ScannedGenericBeanDefinition
                        ScannedGenericBeanDefinition sbd=new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
                        sbd.setId(beanName);
                        candidates.add(sbd);
                    }
                } catch (Throwable ex) {
                    throw new BeanDefinitionStoreException("Failed to read candidate component class:" + resource, ex
                    );
                }


            }


        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", e);
        }
        return candidates;
    }

}
