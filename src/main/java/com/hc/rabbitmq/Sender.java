package com.hc.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //    public void send() {
//        List<Map> mapList = new ArrayList<>();
//        Map map = new HashMap();
//        map.put("--", "hhhh");
//        map.put(123, 1D);
//        Map map1 = new HashMap();
//        map1.put("a", "123");
//        mapList.add(map);
//        mapList.add(map1);
//        rabbitTemplate.convertAndSend("first", mapList);
//    }
//
//
//    public void send(String message) {
//        //第一个参数指将消息发送到该名称的交换机，第二个参数为对应的routing_key,第三个参数为发送的具体消息
//        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "key.1", message);
//    }
    //首部交换机  x-match
//    public void send(String message) {
//        //第一个参数指将消息发送到该名称的交换机，第二个参数为对应的routing_key(此时设置为空字符串即可),第三个参数为发送的具体消息
////        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topic.1dsaasdasdadas12312sadasdas+++++~!@#$%^&*()_+", message);
////        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topic.2", message);
////        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topic.1.1", message);
//
//
//        //配置消息规则
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("headers1", "value1");
////        messageProperties.setHeader("headers2", "value2");
//        messageProperties.setHeader("headers3", "value3");
//        messageProperties.setHeader("headers4", "value4");
//
//        //要发送的消息，第一个参数为具体的消息字节数组，第二个参数为消息规则
//        Message msg = new Message(message.getBytes(), messageProperties);
//        rabbitTemplate.convertAndSend("HEADERS_EXCHANGE", "", msg);
//
//    }


}
