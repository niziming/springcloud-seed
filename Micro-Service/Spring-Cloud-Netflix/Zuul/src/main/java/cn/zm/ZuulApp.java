package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// 入口applicaton类加上注解@EnableZuulProxy，开启zuul的功能
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
    }
}
