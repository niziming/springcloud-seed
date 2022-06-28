package cn.zm.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderApp {
  public static void main(String[] args) {
    SpringApplication.run(NacosProviderApp.class);
  }

  @GetMapping("/hi")
  public String hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name) {
    return "hi " + name;
  }
}
