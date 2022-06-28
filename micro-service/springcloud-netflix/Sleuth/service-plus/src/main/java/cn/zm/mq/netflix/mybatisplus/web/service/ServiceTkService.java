package cn.zm.mq.netflix.mybatisplus.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-tk-8710")
public interface ServiceTkService {
    @GetMapping("/account/ribbon/service")
    String getTkInfo();
}
