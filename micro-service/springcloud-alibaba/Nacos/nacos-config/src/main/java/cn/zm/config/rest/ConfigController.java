package cn.zm.config.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "配置控制")
@RestController
@RefreshScope
public class ConfigController {
  @Value("${username}")
  private String username;

  @ApiOperation("获取配置")
  @GetMapping("/username")
  public String get() {
    return username;
  }

}
