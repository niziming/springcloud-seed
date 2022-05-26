package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jermaine
 */
@SpringBootApplication
// @SpringBootApplication(scanBasePackages = {"cn.zm"})
public class SecurityAppLogin {
  public static void main(String[] args) {
    SpringApplication.run(SecurityAppLogin.class, args);
  }
}
