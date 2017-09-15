package com.zxc;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Unit test for simple App.
 * 单元测试
 * dev测试
 */
public class AppTest extends TestCase {

    public void testApp() {
        List<String> list = null;
        List<String> list1 = new ArrayList<>();

        boolean b = Optional.of(list1).isPresent();
        System.out.println(b);

    }
}
