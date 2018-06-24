package com.jay.spring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanFactoryTest.class,
        ResourceTest.class})
public class V1AllTests {

}
