package cn.zm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
public class TkMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(TkMybatisApp.class, args);
    }
}
