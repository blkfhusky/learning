package com.zxc;

import com.zxc.multiThread.Single;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int a = 18;
        fix01(a);
        System.out.println(a);

    }

    public static int fix01(int a) {
        a = 10;
        return a;
    }
}
