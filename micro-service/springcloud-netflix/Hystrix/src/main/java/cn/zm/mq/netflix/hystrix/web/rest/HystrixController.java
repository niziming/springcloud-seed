package cn.zm.mq.netflix.hystrix.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.mq.netflix.hystrix.web.service.HystrixService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("hystrix/test")
@RestController
@Api(tags = "Hystrix-熔断测试接口")
public class HystrixController {
    @Resource
    HystrixService ribbonService;

    @GetMapping
    @ApiOperation("查询测试")
    public ResResult get() {
        return ResResult.succ(JSON.parse(ribbonService.ribbonHystrix()));
    }

}
