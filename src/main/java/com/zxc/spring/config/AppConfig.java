package com.zxc.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
@Configuration
@Import({CustomerConfig.class, SchedulerConfig.class})
public class AppConfig {
}
