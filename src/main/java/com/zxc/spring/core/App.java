package com.zxc.spring.core;

import com.zxc.spring.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerBo customer = (CustomerBo) context.getBean("customer");
        customer.printMsg("Hello 1");
        SchedulerBo scheduler = (SchedulerBo) context.getBean("scheduler");
        scheduler.printMsg("Hello 2");

    }
}
