package cn.zm.netflix.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"cn.zm.common.config", "cn.zm.plus.config"})
public class NetFlixApp {
    public static void main(String[] args) {
        SpringApplication.run(NetFlixApp.class, args);
    }
}
