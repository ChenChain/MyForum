package com.chain.sms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息监听器
 */
//@Slf4j
//@Component
//@RabbitListener(queues = "sms")
//public class SmsListener {
//
//    /**
//     * 处理消息
//     * @param map
//     */
//    @RabbitHandler
//    public void  executeSms(Map<String,String> map){
//        log.info("消息处理 mobile:{} , checkCode:{}",map.get("mobile"),map.get("code"));
//    }
//}
