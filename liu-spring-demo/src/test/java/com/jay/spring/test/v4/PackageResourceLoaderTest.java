package com.jay.spring.test.v4;

import com.jay.spring.core.io.Resource;
import com.jay.spring.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class PackageResourceLoaderTest {
    @Test
    public void  testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.jay.spring.dao.v4");
        Assert.assertEquals(2,resources.length);
    }
}
