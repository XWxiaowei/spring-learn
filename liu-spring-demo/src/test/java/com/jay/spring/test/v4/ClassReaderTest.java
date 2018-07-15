package com.jay.spring.test.v4;

import com.jay.spring.core.annotation.AnnotationAttributes;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.jay.spring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class ClassReaderTest {

   @Test
   public void  testGetClasMetaData() throws IOException {
       ClassPathResource resource = new ClassPathResource("com/jay/spring/service/v4/PetStoreService.class");
       ClassReader reader = new ClassReader(resource.getInputStream());

       ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
       reader.accept(visitor, ClassReader.SKIP_DEBUG);
       Assert.assertFalse(visitor.isAbstract());
       Assert.assertFalse(visitor.isInterface());
       Assert.assertFalse(visitor.isFinal());
       Assert.assertEquals("com.jay.spring.service.v4.PetStoreService", visitor.getClassName());
       Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
       Assert.assertEquals(0, visitor.getInterfaceNames().length);

   }
   @Test
    public void  testGetAnnonation() throws IOException {
       ClassPathResource resource = new ClassPathResource("com/jay/spring/service/v4/PetStoreService.class");
       ClassReader reader = new ClassReader(resource.getInputStream());

       AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
       reader.accept(visitor, ClassReader.SKIP_DEBUG);

       String annotation = "com.jay.spring.stereotype.Component";
       Assert.assertTrue(visitor.hasAnnotation(annotation));

       AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
       Assert.assertEquals("petStoreService", attributes.get("value"));

   }

}
