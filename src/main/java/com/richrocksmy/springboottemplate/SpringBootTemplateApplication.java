package com.richrocksmy.springboottemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootTemplateApplication {

    public static void main(String... args) {
        SpringApplication.run(SpringBootTemplateApplication.class, args);
    }
}


