// package cn.zm.mq.config;
//
// import org.springframework.amqp.core.Queue;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
// import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class RabbitMQConfig {
//   @Value("${spring.rabbitmq.queue}")
//   String queueName;
//
//   @Value("${spring.rabbitmq.exchange}")
//   String exchange;
//
//   @Value("${spring.rabbitmq.routingkey}")
//   private String routingkey;
//
// }
