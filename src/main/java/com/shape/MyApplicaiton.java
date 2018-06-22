package com.shape;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.shape.dao")
@EnableTransactionManagement
public class MyApplicaiton extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyApplicaiton.class);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyApplicaiton.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
