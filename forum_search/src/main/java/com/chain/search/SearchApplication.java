package com.chain.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/24
 */
@SpringBootApplication
public class SearchApplication {
    @Bean
    public IdWorker idWorker(){
        return  new IdWorker(1,1);
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
