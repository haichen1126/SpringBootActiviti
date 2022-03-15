package com.hc.controller;

import com.hc.rabbitmq.RabbitMQConfig;
import com.hc.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class rabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testRabbitmq() throws Exception {
//        sender.send();
//        sender.send("123");
        for (int i = 0; i < 10000; i++) {
            /*MessagePostProcessor messagePostProcessor=message -> {
                MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
                messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
                messageProperties.setExpiration("5000");
                return message;
            };*/
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_BOOT,
                    "direct_queue1", i+"");

        }
    }


}
