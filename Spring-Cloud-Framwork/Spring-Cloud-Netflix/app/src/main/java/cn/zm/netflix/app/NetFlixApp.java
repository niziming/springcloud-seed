package cn.zm.netflix.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NetFlixApp {
    public static void main(String[] args) {
        SpringApplication.run(NetFlixApp.class, args);
    }
}
