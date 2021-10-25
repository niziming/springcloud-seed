package cn.zm.netflix.feign.web.service.impl;

import cn.zm.netflix.feign.web.service.FeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class HystrixHandler implements FeignHystrixService {
    @Override
    public String testHystrix() {
        return "{'info': 'hystrix feign'}";
    }
}
