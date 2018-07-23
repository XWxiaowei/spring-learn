package com.jay.spring.test.v4;

import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.annotation.AutowiredFieldElement;
import com.jay.spring.bean.factory.annotation.InjectionElement;
import com.jay.spring.bean.factory.annotation.InjectionMetadata;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
import com.jay.spring.dao.v4.AccountDao;
import com.jay.spring.dao.v4.ItemDao;
import com.jay.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class InjectionMetadataTest {
    @Test
    public void testInjection() throws NoSuchFieldException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Class<?> clz = PetStoreService.class;
        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
        {
            Field f = PetStoreService.class.getDeclaredField("accountDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f, true, factory);
            elements.add(injectionElem);
        }
        {
            Field f = PetStoreService.class.getDeclaredField("itemDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f,true,factory);
            elements.add(injectionElem);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz, elements);
        PetStoreService petStoreService = new PetStoreService();
        metadata.inject(petStoreService);
        Assert.assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStoreService.getItemDao() instanceof ItemDao);

    }
}
