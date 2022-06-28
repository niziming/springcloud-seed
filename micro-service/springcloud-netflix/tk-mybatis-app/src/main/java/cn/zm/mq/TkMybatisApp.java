package cn.zm.mq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TkMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(TkMybatisApp.class, args);
    }
}
