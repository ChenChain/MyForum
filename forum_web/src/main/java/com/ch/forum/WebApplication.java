package com.ch.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chenqian091
 * @date 2020-07-20
 */
@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
public class WebApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}