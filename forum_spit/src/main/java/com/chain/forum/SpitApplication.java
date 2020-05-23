package com.chain.forum;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/23
 */
@SpringBootApplication
public class SpitApplication {

    @Bean
    public IdWorker idWorker(){
        return  new IdWorker(1,1);
    }


}
