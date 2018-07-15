package com.jay.spring.test.v4;

import com.jay.spring.core.annotation.AnnotationAttributes;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.type.AnnotationMetadata;
import com.jay.spring.core.type.classreading.MetadataReader;
import com.jay.spring.core.type.classreading.SimpleMetadataReader;
import com.jay.spring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/jay/spring/service/v4/PetStoreService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);

        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStoreService", attributes.get("value"));

        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("com.jay.spring.service.v4.PetStoreService", amd.getClassName());
    }
}
