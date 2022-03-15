package com.hc.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component//用于被Spring所识别管理
public class ConsumerListener {


    @RabbitListener(queues = "queue_name")//该注解写在一个方法上,代表着该方法不会被自动停止,会持续监听某个队列. queues的属性值为要监听的队列名
    public void listenerQueue(Message messageObj) { //Message用于封装接收到的数据
        //获取配置信息
        String contentType = messageObj.getMessageProperties().getContentType();
        System.out.println("配置信息-contentType:" + contentType);

        //获取传递过来的消息
        byte[] body = messageObj.getBody();
        System.out.println("消息内容为:" + new String(body));

        System.out.println("↓以下为整体数据信息↓");
        System.out.println(messageObj);

    }
}

