package com.jay.spring.test.v1;

import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.FileSystemResource;
import com.jay.spring.core.io.Resource;
import org.junit.Test;

import java.io.IOException;
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
        InputStream is = null;
        try {
            try {
                is = classPathResource.getInputStream();
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("/Volumes/Develop/spring-source/spring-learn/liu-spring-demo/src/test/resources/petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
