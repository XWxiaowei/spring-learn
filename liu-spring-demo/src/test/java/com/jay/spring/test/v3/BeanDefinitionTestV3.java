package com.jay.spring.test.v3;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.ConstructorArgument;
import com.jay.spring.bean.ConstructorArgument.ValueHolder;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.RuntimeBeanReference;
import com.jay.spring.bean.factory.config.TypedStringValue;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiang.wei on 2018/7/7
 *
 *
 * @author xiang.wei
 */
public class BeanDefinitionTestV3 {
    @Test
    public void test() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStoreService");

        Assert.assertEquals("com.jay.spring.service.v3.PetStoreService", bd.getBeanClassName());

        ConstructorArgument args = bd.getConstructorArgument();
        List<ValueHolder> valueHolderList = args.getArgumentValues();

        Assert.assertEquals(3, valueHolderList.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference) valueHolderList.get(0).getValue();
        Assert.assertEquals("accountDao", ref1.getBeanName());

        RuntimeBeanReference ref2 = (RuntimeBeanReference) valueHolderList.get(1).getValue();
        Assert.assertEquals("itemDao", ref2.getBeanName());

        TypedStringValue strValue = (TypedStringValue) valueHolderList.get(2).getValue();
        Assert.assertEquals("1", strValue.getValue());


    }
}
