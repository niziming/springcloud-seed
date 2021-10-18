package zm.netflix.feign.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zm.netflix.feign.web.service.FeignService;

import javax.annotation.Resource;

@Service
public class FeignServiceImpl implements FeignService {
    @Resource
    RestTemplate restTemplate;

    @Override
    public String consume() {
        return restTemplate.getForObject("http://SERVICE-APP/account/ribbon/service", String.class);
    }
}
