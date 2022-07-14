package cn.zm.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {


  // Create Queues named - marketingQueue, adminQueue, financeQueue
  @Bean
  Queue marketingQueue() {
    return new Queue("marketingQueue", false);
  }

  // Create Queues named - marketingQueue, adminQueue, financeQueue
  @Bean
  Queue financeQueue() {
    return new Queue("financeQueue", false);
  }

  // Create Queues named - marketingQueue, adminQueue, financeQueue
  @Bean
  Queue adminQueue() {
    return new Queue("adminQueue", false);
  }

  // Create a DirectExchange named - direct-exchange
  @Bean("directExchange")
  DirectExchange exchange() {
    return new DirectExchange("direct-exchange");
  }

  // Create Bindings for each of the queue with the DirectExchange specifying the binding key
  @Bean
  Binding marketingBinding(Queue marketingQueue, DirectExchange exchange) {
    return BindingBuilder.bind(marketingQueue).to(exchange).with("marketing");
  }

  // Create Bindings for each of the queue with the DirectExchange specifying the binding key
  @Bean
  Binding financeBinding(Queue financeQueue, DirectExchange exchange) {
    return BindingBuilder.bind(financeQueue).to(exchange).with("finance");
  }

  // Create Bindings for each of the queue with the DirectExchange specifying the binding key
  @Bean
  Binding adminBinding(Queue adminQueue, DirectExchange exchange) {
    return BindingBuilder.bind(adminQueue).to(exchange).with("admin");
  }
}
