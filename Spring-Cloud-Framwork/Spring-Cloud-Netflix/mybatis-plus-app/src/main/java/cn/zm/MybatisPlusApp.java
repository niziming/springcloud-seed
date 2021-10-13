package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MybatisPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
    }
}
