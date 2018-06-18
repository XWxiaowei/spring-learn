package com.jay.spring;

import com.jay.spring.core.io.ClassPathResource;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ResourceTest {
    @Test
    public void testClassPathResource() {
        ClassPathResource classPathResource = new ClassPathResource("petstore-v1.xml");

        InputStream inputStream = classPathResource.getInputStream();
    }
}
