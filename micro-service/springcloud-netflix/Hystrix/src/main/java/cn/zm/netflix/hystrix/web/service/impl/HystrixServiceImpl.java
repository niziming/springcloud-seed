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

    /** 功能描述: <br>
     * <@HystrixCommand注解。该注解对该方法创建了熔断器的功能，
     * 并指定了fallbackMethod熔断方法，熔断方法直接返回了一个字符串，
     * 字符串为{'info': 'hystrix handler'}，代码如下：
     *
     * @Service>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/22 10:28
     * @return java.lang.String
     */
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
