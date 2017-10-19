package com.zxc.annotation;

import java.lang.reflect.Method;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/10/13.
 * <p>
 * ------------------ _ooOoo_-------------------
 * ------------------o8888888o------------------
 * ------------------88" . "88------------------
 * ------------------(| -_- |)------------------
 * ------------------O\  =  /O------------------
 * ---------------____/`---'\____---------------
 * -------------.'  \\|     |//  `.-------------
 * ------------/  \\|||  :  |||//  \------------
 * -----------/  _||||| -:- |||||-  \-----------
 * -----------|   | \\\  -  /// |   |-----------
 * -----------| \_|  ''\---/''  |   |-----------
 * -----------\  .-\__  `-`  ___/-. /-----------
 * ---------___`. .'  /--.--\  `. . __----------
 * ------."" '<  `.___\_<|>_/___.'  >'"".-------
 * -----| | :  `- \`.;`\ _ /`;.`/ - ` : | |-----
 * -----\  \ `-.   \_ __\ /__ _/   .-` /  /-----
 * ======`-.____`-.___\_____/___.-`____.-'======
 * -------------------`=---='-------------------
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * -----------佛祖保佑 BUG DIE DIE DIE-----------
 * =============================================
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException {
        classTest();
        methodTest();
    }

    public static void classTest() {
        Class<Child> c = Child.class;
        if (c.isAnnotationPresent(InheritedTest.class)) {
            InheritedTest inheritedTest = c.getAnnotation(InheritedTest.class);
            String value = inheritedTest.value();
            System.out.println(value);
        }

    }

    public static void methodTest() throws SecurityException, NoSuchMethodException {
        Class<Child> c = Child.class;
        Method method = c.getMethod("doSomething", new Class[]{});

        if (method.isAnnotationPresent(InheritedTest.class)) {
            InheritedTest inheritedTest = method.getAnnotation(InheritedTest.class);
            String value = inheritedTest.value();
            System.out.println(value);
        }
    }
}
