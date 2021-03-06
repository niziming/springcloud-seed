package cn.zm.mq;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启AdminServer的功能
@EnableAdminServer
@SpringBootApplication
public class AdminServerApp {
  public static void main(String[] args) {
    SpringApplication.run(AdminServerApp.class, args);
  }
}
