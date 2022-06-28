package cn.zm.mq.controller;

import cn.zm.mq.entity.Employee;
import cn.zm.mq.service.RabbitMQSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "消息生产者")
@RequestMapping(value = "/javainuse-rabbitmq/")
public class RabbitMQWebController {

  @Autowired
  RabbitMQSender rabbitMQSender;

  @GetMapping(value = "/producer")
  public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

    Employee emp=new Employee();
    emp.setId(empId);
    emp.setName(empName);
    rabbitMQSender.send(emp);

    return "Message sent to the RabbitMQ JavaInUse Successfully";
  }

}
