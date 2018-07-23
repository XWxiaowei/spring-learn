package com.jay.spring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by xiang.wei on 2018/7/7
 *
 * @author xiang.wei
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ApplicationContextTestV3.class, BeanDefinitionTestV3.class, ConstructorResolverTest.class })
public class V3AllTests {
}
