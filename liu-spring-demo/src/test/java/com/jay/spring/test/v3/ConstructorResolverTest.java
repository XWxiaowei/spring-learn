package com.jay.spring.test.v3;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.ConstructorResolver;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/7/7
 *
 * @author xiang.wei
 */
public class ConstructorResolverTest {
    @Test
    public void test() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));


        BeanDefinition bd = factory.getBeanDefinition("petStoreService");

        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStoreService petStoreService = (PetStoreService) resolver.autowireConstructor(bd);

        Assert.assertEquals(1, petStoreService.getVersion());
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

    }
}
