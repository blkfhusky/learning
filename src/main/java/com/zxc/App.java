package com.zxc;

import com.zxc.flowable.boot.PhotoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {

    @Bean
    CommandLineRunner init(final PhotoService photoService) {
        return strings -> photoService.launchPhotoProcess("one", "two", "three");
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
