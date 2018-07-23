package com.jay.spring.test.v4;

import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.DependencyDescriptor;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
import com.jay.spring.dao.v4.AccountDao;
import com.jay.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class DependencyDescriptorTest {
    @Test
    public void  testResolveDependency() throws NoSuchFieldException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);


        Field field = PetStoreService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(field,true);
        Object o = factory.resolveDependency(descriptor);
        Assert.assertTrue(o instanceof AccountDao);

    }
}
