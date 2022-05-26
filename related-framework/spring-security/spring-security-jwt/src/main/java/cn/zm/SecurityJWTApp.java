package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jermaine
 */
@SpringBootApplication(scanBasePackages = {"cn.zm"})
public class SecurityJWTApp {
  public static void main(String[] args) {
    SpringApplication.run(SecurityJWTApp.class, args);
  }
}
