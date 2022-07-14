package cn.zm.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.zm")
public class ConsumeMSG {
  public static void main(String[] args) {
    SpringApplication.run(ConsumeMSG.class, args);
  }
}
