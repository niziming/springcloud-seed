package cn.zm.netflix.tkmybatis.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-plus-8709")
public interface PlusService {
    @GetMapping("/account/ribbon/service")
    String getPlusInfo();
}
