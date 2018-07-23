package com.jay.spring.test.v4;

import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.annotation.AutowiredAnnotationProcessor;
import com.jay.spring.bean.factory.annotation.AutowiredFieldElement;
import com.jay.spring.bean.factory.annotation.InjectionElement;
import com.jay.spring.bean.factory.annotation.InjectionMetadata;
import com.jay.spring.bean.factory.config.DependencyDescriptor;
import com.jay.spring.dao.v4.AccountDao;
import com.jay.spring.dao.v4.ItemDao;
import com.jay.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class AutowiredAnnotationProcessorTest {
    AccountDao accountDao = new AccountDao();
    ItemDao itemDao = new ItemDao();


    DefaultBeanFactory beanFactory = new DefaultBeanFactory() {
        @Override
        public Object resolveDependency(DependencyDescriptor descriptor) {
            if (descriptor.getDepencyType().equals(AccountDao.class)) {
                return accountDao;
            }
            if (descriptor.getDepencyType().equals(ItemDao.class)) {
                return itemDao;
            }
            throw new RuntimeException("can't support types except AccountDao and ItemDao");
        }
    };
    @Test
    public void testGetInjectionMetadata() {
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();

        processor.setBeanFactory(beanFactory);
        InjectionMetadata injectionMetadata=processor.buildAutowiringMetadata(PetStoreService.class);

        List<InjectionElement> elementList = injectionMetadata.getInjectionElementList();
        Assert.assertEquals(2, elementList.size());

        assertFieldExists(elementList,"accountDao");
        assertFieldExists(elementList,"itemDao");

        PetStoreService petStore = new PetStoreService();

        injectionMetadata.inject(petStore);

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);

    }

    private void assertFieldExists(List<InjectionElement> elements ,String fieldName){
        for(InjectionElement ele : elements){
            AutowiredFieldElement fieldEle = (AutowiredFieldElement)ele;
            Field f = fieldEle.getField();
            if(f.getName().equals(fieldName)){
                return;
            }
        }
        Assert.fail(fieldName + "does not exist!");
    }

}
