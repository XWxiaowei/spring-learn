package com.jay.spring.test;

import com.jay.spring.test.v1.V1AllTests;
import com.jay.spring.test.v2.V2AllTests;
import com.jay.spring.test.v3.V3AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({V1AllTests.class,V2AllTests.class, V3AllTests.class})
public class AllTests {

}
