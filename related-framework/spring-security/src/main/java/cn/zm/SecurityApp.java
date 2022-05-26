package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jermaine
 */
@SpringBootApplication(scanBasePackages = {"cn.zm"})
public class SecurityApp {
  public static void main(String[] args) {
    SpringApplication.run(SecurityApp.class, args);
  }
}
