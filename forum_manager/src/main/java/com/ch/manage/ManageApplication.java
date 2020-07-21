package com.ch.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JWTUtil;

/**
 * @author chenqian091
 * @date 2020-07-20
 */

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
public class ManageApplication {


    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class);
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }
}
