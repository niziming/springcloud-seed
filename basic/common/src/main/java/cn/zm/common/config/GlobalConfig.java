package cn.zm.common.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 十渊
 */
@Component
@Data
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {
    private Map<String, JSONObject> config;
    private Map<String, JSONObject> netty;
}
// /**
//  * @author 十渊
//  */
// @Component
// @Data
// @ConfigurationProperties(prefix = "config")
// public class GlobalConfig {
//     private Map<String, JSONObject> netty;
// }
