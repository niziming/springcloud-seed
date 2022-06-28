package cn.zm.mq;

import cn.zm.mq.nacos.service.ProviderClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsuerApp {
  public static void main(String[] args) {
    SpringApplication.run(NacosConsuerApp.class);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RestController
  public class ConsumerController {

    @Resource
    RestTemplate restTemplate;

    @Resource
    ProviderClient providerClient;

    @GetMapping("/hi-feign")
    public String hiFeign(){
      return providerClient.hi("feign");
    }

    @GetMapping("/hi-rest-template")
    public String hiResttemplate() {
      return restTemplate.getForObject("http://nacos-provider-8714/hi?name=resttemplate", String.class);

    }
  }

}
