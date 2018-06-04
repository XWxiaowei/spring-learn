package com.jay.spring.io;

import com.jay.spring.beans.io.Resource;
import com.jay.spring.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class ResourceTest {
    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource urlResource = resourceLoader.getResource("tinyioc.xml");
        InputStream inputStream = urlResource.getInputStream();
        Assert.assertNotNull(inputStream);

    }
}
