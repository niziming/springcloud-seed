package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 在程序的入口Application类加上@EnableConfigServer注解开启配置服务器的功能
@EnableConfigServer
@EnableEurekaClient
// @RestController
@SpringBootApplication
public class ConfigServerApp {
    //
    // @RequestMapping("/")
    // public String home() {
    //     return "Hello World!";
    // }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
