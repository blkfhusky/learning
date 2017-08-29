package com.zxc.spring.config;

import com.zxc.spring.core.SchedulerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
@Configuration
public class SchedulerConfig {

    @Bean(name = "scheduler")
    public SchedulerBo schedulerBo() {
        return new SchedulerBo();
    }

}
