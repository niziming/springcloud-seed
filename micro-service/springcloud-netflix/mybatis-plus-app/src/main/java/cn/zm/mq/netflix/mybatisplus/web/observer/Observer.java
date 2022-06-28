package cn.zm.mq.netflix.mybatisplus.web.observer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * 观察者模式
 * @author 十渊Jermaine
 */
@Getter
@Setter
public class Observer extends ApplicationEvent {
  private static final long serialVersionUID = 1L;
  private String msg;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param source the object on which the event initially occurred or with
   *               which the event is associated (never {@code null})
   */
  public Observer(Object source, String msg) {
    super(source);
    this.msg = msg;
  }


}
