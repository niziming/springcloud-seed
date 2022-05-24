package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableEurekaClient
@SpringBootApplication
// @ComponentScans(
//   {
//     @ComponentScan("cn.zm.netflix")
//   }
// )
public class MybatisPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
    }
}
