package cn.zm.mq.tk.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("cn.zm.**.mapper")
public class MapperConfig {
}
