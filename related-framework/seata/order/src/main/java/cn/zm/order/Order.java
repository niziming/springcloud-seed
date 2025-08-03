package cn.zm.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Order {
    public static void main(String[] args) {
        SpringApplication.run(Order.class, args);
    }

}
