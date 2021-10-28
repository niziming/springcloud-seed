package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("cn.zm.**.mapper")
public class ServiceTkApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTkApp.class, args);
    }
}
