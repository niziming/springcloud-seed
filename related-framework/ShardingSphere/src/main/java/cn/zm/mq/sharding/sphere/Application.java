package cn.zm.mq.sharding.sphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.zm.**.mapper"})
public class Application {
  public static void main(String[] args) {
  }
}
