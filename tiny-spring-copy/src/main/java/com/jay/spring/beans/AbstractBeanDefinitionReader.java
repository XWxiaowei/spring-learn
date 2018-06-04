package com.jay.spring.beans;

import com.jay.spring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/4 15:42
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
     /**
      * 注册的bean
      */
     private Map<String, BeanDefinition> registry;

     private ResourceLoader resourceLoader;

     public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
          this.registry = new HashMap<String, BeanDefinition>();
          this.resourceLoader = resourceLoader;
     }

     public Map<String, BeanDefinition> getRegistry() {
          return registry;
     }

     public ResourceLoader getResourceLoader() {
          return resourceLoader;
     }
}
