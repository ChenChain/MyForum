package com.forum.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JWTUtil;

/**
 * @author chenqian091
 * @date 2020-07-14
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }


}
