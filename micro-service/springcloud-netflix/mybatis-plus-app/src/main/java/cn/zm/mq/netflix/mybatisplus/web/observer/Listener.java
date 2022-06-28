package cn.zm.mq.netflix.mybatisplus.web.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener implements ApplicationListener<Observer> {

  @Override
  public void onApplicationEvent(Observer event) {
    String msg = event.getMsg();
    log.info("接收到的消息是-{}", msg);

  }
}