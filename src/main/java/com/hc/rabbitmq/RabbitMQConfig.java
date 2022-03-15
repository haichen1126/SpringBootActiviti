package com.hc.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

//    @Bean
//    public Queue firstQueue() {
//        // 创建一个队列，名称为：first
//        return new Queue("first",true);
//    }
//
//    @Bean
//    public Queue first1Queue() {
//        // 创建一个队列，名称为：first durable是否持久化
//        return new Queue("first1", true);
//    }
//
//    @Bean
//    public Queue miaoShaQueue() {
//        return new Queue("queue_name", true);
//    }
//    @Bean
//    public Queue first2Queue() {
//        return new Queue("first2", true);
//    }


    //创建直连交换机，参数为交换机的名称
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("DIRECT_EXCHANGE");
//    }
    //创建扇形交换机，参数为交换机的名称
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange ("FANOUT_EXCHANGE");
//    }

    //将三个队列都与该直连交换机绑定起来，并赋予上面说的binding_key(也可以说是routing_key)
//    @Bean
//    public Binding bindingDirectExchange1() {
//        return BindingBuilder.bind(firstQueue()).to(directExchange()).with("key.1");
//    }
//
//    @Bean
//    public Binding bindingDirectExchange2() {
//        return BindingBuilder.bind(first1Queue()).to(directExchange()).with("key.1");
//    }
//
//    @Bean
//    public Binding bindingDirectExchange3() {
//        return BindingBuilder.bind(miaoShaQueue()).to(directExchange()).with("key.2");
//    }


    //将三个队列都与该交换机绑定起来，无需binding_key 扇形交换机
//    @Bean
//    public Binding bindingFanoutExchange1() {
//        return BindingBuilder.bind(firstQueue()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding bindingFanoutExchange2() {
//        return BindingBuilder.bind(first1Queue()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding bindingFanoutExchange3() {
//        return BindingBuilder.bind(first2Queue()).to(fanoutExchange());
//    }

//    主题交换机
//    @Bean
//    public Queue topicQueue1() {
//        return new Queue("TOPIC_QUEUE1", true);
//    }
//    @Bean
//    public Queue topicQueue2() {
//        return new Queue("TOPIC_QUEUE2", true);
//    }
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange("TOPIC_EXCHANGE");
//    }
//    //将topicQueue1与topicExchange交换机绑定
//    @Bean
//    public Binding bindQueue1() {
//        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.*");
//    }
//    //将topicQueue2与topicExchange交换机绑定
//    @Bean
//    public Binding bindQueue2() {
//        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
//    }



//    @Bean
//    public Queue headersQueue() {
//        return new Queue("HEADERS_QUEUE");
//    }
//    @Bean
//    public HeadersExchange headersExchange() {
//        return new HeadersExchange("HEADERS_EXCHANGE");
//    }
//    //将headersQueue与HeadersExchange交换机绑定
//    @Bean
//    public Binding bingHeadersQueue() {
//        //map为绑定的规则
//        Map<String, Object> map = new HashMap<>();
//        map.put("headers1", "value1");
//        map.put("headers2", "value2");
//        //whereAll表示需要满足所有条件
//        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAny(map).match();
//    }




    /**
     * 正常交换机的名称
     */
    public static final String TOPIC_EXCHANGE_BOOT = "direct_exchange-boot";
    /**
     * s信交换机的名称
     */
    public static final String DLX_EXCHANGE_BOOT = "dlx_exchange-boot";
    /**
     * 正常队列的名称
     */
    public static final String TOPIC_QUEUE1_BOOT = "direct_queue1";
    /**
     * s信队列的名称
     */
    public static final String DLX_QUEUE1_BOOT = "dlx_queue1";


    /**
     * 声明队列
     *
     * @return
     */
    @Bean("itemqueue")
    public Queue queueDeclare() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_BOOT);
        // x-dead-letter-routing-key
        args.put("x-dead-letter-routing-key", DLX_QUEUE1_BOOT);
        //x-message-ttl
        args.put("x-message-ttl", 9000);
        //x-max-length
        args.put("x-max-length", 99);
        return QueueBuilder.durable(TOPIC_QUEUE1_BOOT).withArguments(args).build();
    }

    /**
     * 声明死信队列
     *
     * @return
     */
    @Bean("dlxqueue")
    public Queue queueDlx() {
        return QueueBuilder.durable(DLX_QUEUE1_BOOT).build();
    }

    /**
     * 声明s信交换机
     *
     * @return
     */
    @Bean("dlxexchange")
    public Exchange exchangeDlx() {
        //return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_BOOT).durable(true).build();
        return ExchangeBuilder.directExchange(DLX_EXCHANGE_BOOT).durable(true).build();
    }

    /**
     * 声明正常的交换机
     *
     * @return
     */
    @Bean("exchange")
    public Exchange exchangetopic() {
        //return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_BOOT).durable(true).build();
        return ExchangeBuilder.directExchange(TOPIC_EXCHANGE_BOOT).durable(true).build();
    }

    /**
     * 队列绑定交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding queueBindExchange(@Qualifier("itemqueue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        //return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
        return BindingBuilder.bind(queue).to(exchange).with("direct_queue1").noargs();
    }


    /**
     * s信队列绑定s信交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding dlxQueueBindDlxExchange(@Qualifier("dlxqueue") Queue queue, @Qualifier("dlxexchange") Exchange exchange) {
        //return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
        return BindingBuilder.bind(queue).to(exchange).with("dlx_queue1").noargs();
    }





}
