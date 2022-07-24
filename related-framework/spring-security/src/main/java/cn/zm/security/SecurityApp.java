package cn.zm.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jermaine
 */
@SpringBootApplication
@MapperScan("cn.zm.**.mapper")
// @SpringBootApplication(scanBasePackages = {"cn.zm"})
public class SecurityApp {
  public static void main(String[] args) {
    SpringApplication.run(SecurityApp.class, args);
  }
}
