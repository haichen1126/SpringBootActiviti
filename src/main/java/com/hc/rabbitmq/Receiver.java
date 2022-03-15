package com.hc.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "first") //定义该类需要监听的队列
@Slf4j
public class Receiver {

//    @RabbitHandler//指定对消息的处理
//    public void process(Object msg) {
//        System.out.println(msg.toString());
//    }
//
//
//    @RabbitListener(queues = "first")
//    public void receive1(String msg) {
//        log.info("first:——————————————————————————");
//        log.info("first:{}", msg);
//    }
//
//    @RabbitListener(queues = "first1")
//    public void receive2(String msg) {
//        log.info("first1:——————————————————————————");
//        log.info("first1:{}", msg);
//    }
//
//    @RabbitListener(queues = "first2")
//    public void receive3(String msg) {
//        log.info("first2:——————————————————————————");
//        log.info("first2:{}", msg);
//    }


    //    @RabbitListener(queues = "TOPIC_QUEUE1")
//    public void receiveQueue1(String message) {
//        log.info("receive : queue1 {}", message);
//    }
//
//    @RabbitListener(queues ="TOPIC_QUEUE2")
//    public void receiveQueue2(String message) {
//        log.info("receive : queue2 {}", message);
//    }
//    @RabbitListener(queues = "HEADERS_QUEUE")
//    public void receiveHeadersQueue(byte[] message) {
//        log.info("receive : HeadersQueue {}", new String(message));
//    }
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Test
//    public void test(){
//        for (int i = 0; i < 10000; i++) {
//            /*MessagePostProcessor messagePostProcessor=message -> {
//                MessageProperties messageProperties = message.getMessageProperties();
////            设置编码
//                messageProperties.setContentEncoding("utf-8");
////            设置过期时间10*1000毫秒
//                messageProperties.setExpiration("5000");
//                return message;
//            };*/
//            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_BOOT,
//                    "direct_queue1", i+"");
//
//        }
//    }




}
