package cn.zm.common.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ASUS
 */
@Component
@Data
@ConfigurationProperties(prefix = "config")
public class GlobalConfig {
    private Map<String, JSONObject> netty;
}
