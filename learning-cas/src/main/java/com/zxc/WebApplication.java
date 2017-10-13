package com.zxc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/9/22.
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
@Configuration
public class WebApplication {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*");
            }
        };
    }
}
