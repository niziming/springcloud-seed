package cn.zm.tk.cinfig;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("cn.zm.**.mapper")
public class MapperConfig {
}
