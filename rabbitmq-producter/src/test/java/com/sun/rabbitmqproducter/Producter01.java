package com.sun.rabbitmqproducter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 入门程序
 */
public class Producter01 {
    //队列
    private static final String QUEUE="helloworld";

    public static void main(String[] args) {
        //和mq连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);//端口
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每一个虚拟机就相当于一个独立的mq
        connectionFactory.setVirtualHost("/");
        Connection conn = null;
        Channel channel = null;
        try {
            conn = connectionFactory.newConnection();
            //创建会话通道，生产者和mq服务的所有通信都在channel通道中完成，
            channel =  conn.createChannel();
            //声明队列
            //String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            /**
             * 参数明细
             * 1.queue 队列名称
             * 2.durable 是否持久化，如果持久化，mq重启后队列还在
             * 3.exclusive 是否独占连接，队列只在该连接中访问，如果Connection连接关闭则队列自动删除,如果将参数设置true可用于临时队列的创建
             * 4.autoDelete 自动删除，队列不在使用时是否自动删除队列，如果将此参数和exclusive参数设置为true就可实现临时队列（队列不用了就自动删除）
             * 5.arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
             */
            channel.queueDeclare(QUEUE,true,false,false,null);
            //发送消息
            //参数 String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body
            /**
             * 1.exchange 交换机，如果不指定将使用mq的默认交换机
             * 2.routingKey 路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列名称
             * 3.props 消息的属性
             * 4.body 消息内容
             */
            String message = "hello worild 你好世界";
            channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println("send to mq:"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                try {
                    channel.close();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
