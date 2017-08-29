package com.zxc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 * 单元测试
 * dev测试
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
//        assertTrue( true );
        String remind_return = "1,2,3";

        List<Integer> list = Stream.of(remind_return.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(list);

    }
}
