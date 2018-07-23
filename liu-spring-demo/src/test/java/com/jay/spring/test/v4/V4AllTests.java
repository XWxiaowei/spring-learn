package com.jay.spring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by xiang.wei on 2018/7/23
 *
 * @author xiang.wei
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTestV4.class,AutowiredAnnotationProcessorTest.class,
        ClassPathBeanDefinitionScannerTest.class,ClassReaderTest.class,DependencyDescriptorTest.class,
        InjectionMetadataTest.class,MetadataReaderTest.class,PackageResourceLoaderTest.class,
        XmlBeanDefinitionReaderTest.class})
public class V4AllTests {
}
