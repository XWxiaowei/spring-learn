package com.jay.spring.test;

import com.jay.spring.test.v1.V1AllTests;
import com.jay.spring.test.v2.V2AllTests;
import com.jay.spring.test.v3.V3AllTests;
import com.jay.spring.test.v4.V4AllTests;
import com.jay.spring.test.v5.V5AllTests;
import com.jay.spring.test.v6.V6AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({V1AllTests.class,V2AllTests.class,V3AllTests.class,V4AllTests.class,V5AllTests.class,V6AllTests.class})
public class AllTests {

}
