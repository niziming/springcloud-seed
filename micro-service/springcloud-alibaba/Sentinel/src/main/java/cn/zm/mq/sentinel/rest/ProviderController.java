package cn.zm.mq.sentinel.rest;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
  @GetMapping("/hi")
  @SentinelResource(value="hi")
  public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
    return "hi "+name;
  }
}
