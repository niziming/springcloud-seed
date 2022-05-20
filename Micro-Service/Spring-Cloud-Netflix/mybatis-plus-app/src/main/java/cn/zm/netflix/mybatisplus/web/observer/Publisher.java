package cn.zm.netflix.mybatisplus.web.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// 发布事件，可以通过ApplicationEventPublisher  的 publishEvent() 方法发布消息。
@Component
public class Publisher {

  @Autowired
  ApplicationContext applicationContext;

  public void publish(String message){
    //发布事件
    applicationContext.publishEvent(new Observer(this, message));
  }
}