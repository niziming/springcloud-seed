package cn.zm.netflix.feign.web.service;

import cn.zm.netflix.feign.web.service.impl.HystrixHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "SERVICE-APP", fallback = HystrixHandler.class)
public interface FeignHystrixService {
    @GetMapping("/account/ribbon/service")
    String testHystrix();
}
