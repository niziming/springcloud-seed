package cn.zm.netflix.hystrix.web.service.impl;

import cn.zm.netflix.hystrix.web.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 十渊
 */
@Service
public class HystrixServiceImpl implements HystrixService {
    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hystrixInfo")
    @Override
    public String ribbonHystrix() {
        return restTemplate.getForObject("http://SERVICE-APP/account/ribbon/service", String.class);
    }

    @Override
    public String hystrixInfo() {
        return "{'info': 'hystrix handler'}";
    }
}
