package com.sun.rabbitmqproducter;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.rabbitmqproducter.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@SpringBootTest
@RunWith(SpringRunner.class)
public class Producter05_topics_springboot {
    @Autowired
    RabbitTemplate rabbitTemplate;
    //使用RabbitTemplate发送消息
    String message = "send email message to user";
    /**
     * String exchange, String routingKey, Object object
     * 1.exchange 交换机名称
     * 2.routingKey
     * 3.object 消息
     */
    @Test
    public void testSendEmail(){
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
    }
}
