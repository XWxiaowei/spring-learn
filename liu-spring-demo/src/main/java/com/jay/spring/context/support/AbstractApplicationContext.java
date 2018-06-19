package com.jay.spring.context.support;

import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.context.ApplicationContext;
import com.jay.spring.core.io.Resource;
import com.jay.spring.util.ClassUtil;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory defaultBeanFactory = null;
    private ClassLoader classLoader;



    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
        //TODO 获取classLoader方式有问题
        defaultBeanFactory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return defaultBeanFactory.getBean(beanId);
    }

    public abstract Resource getResourceByPath(String configFile);

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtil.getDefaultClassLoader();
    }
}
