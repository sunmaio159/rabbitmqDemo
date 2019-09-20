package com.sun.rabbitmqconsumer.mq;

import com.rabbitmq.client.Channel;
import com.sun.rabbitmqconsumer.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class ReceiveHandle {
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void send_email(String msg, Message message, Channel channel){
        System.out.println("receive message is: "+ msg);
    }
}
