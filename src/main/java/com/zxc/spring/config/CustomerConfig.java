package com.zxc.spring.config;

import com.zxc.spring.core.CustomerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
@Configuration
public class CustomerConfig {

    @Bean(name = "customer")
    public CustomerBo customerBo() {
        return new CustomerBo();
    }

}
