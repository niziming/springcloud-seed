package cn.zm.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "权限测试接口")
@RestController
@RequestMapping("security")
public class SecurityContoller {

  @ApiOperation("需要认证")
  @GetMapping("authentication")
  public String hello() {
    return "required authentication";
  }

  @ApiOperation("不需要认证")
  @GetMapping("not/authentication")
  public String hi() {
    return "not required authentication";
  }
}
