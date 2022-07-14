package cn.zm.mq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "交换测试")
@RestController
@RequestMapping("/exchange")
public class RabbitMQDirectWebController {
  @Autowired
  private AmqpTemplate amqpTemplate;

  @ApiOperation("直接交换")
  @GetMapping("/direct")
  public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                         @RequestParam("messageData") String messageData) {
    amqpTemplate.convertAndSend(exchange, routingKey, messageData);
    return "Message sent to the RabbitMQ Successfully";
  }

}
