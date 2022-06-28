package cn.zm.security.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author DELL
 */
@Slf4j
@Configuration                            // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling                         // 2.开启定时任务
public class ScheduledTask {
  // @Scheduled(cron = "0 10 8 * * ?")       // 每天早上8:10执行一次
  // public void UpdatePolicies () {
  //   log.info("每天8:10执行一次");
  // }
  // @Scheduled(cron = "1 * * * * ?")        // 每分钟的第一秒触发
  // // @Scheduled(cron = "*/1 * * * * ?")   // 每秒触发
  // public void oneSecedeOnce () {
  //   log.info("一秒一次");
  // }
}
