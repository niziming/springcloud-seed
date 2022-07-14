package cn.zm.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.zm")
public class PushingMSG {
  public static void main(String[] args) {
    SpringApplication.run(PushingMSG.class, args);
  }
}
