package cn.zm.mq.listner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQListner implements MessageListener {
  @Override
  public void onMessage(Message message) {
    log.info("MQ body[{}], message[{}]", new String(message.getBody()), message);
  }
}
