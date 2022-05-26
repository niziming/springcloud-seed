package cn.zm.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecurityContoller {

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  /**
   * 无序登陆
   * @return
   */
  @GetMapping("not/authentication")
  public String hi() {
    return "not/authentication";
  }
}
