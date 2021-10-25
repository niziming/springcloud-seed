package cn.zm.netflix.feign.web.rest;

import cn.zm.common.base.ResponseResult;
import cn.zm.netflix.feign.web.service.FeignHystrixService;
import cn.zm.netflix.feign.web.service.FeignService;
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
@RestController
@RequestMapping("feign")
@Api(tags = "feign测试接口")
public class FeignController {
    @Resource
    FeignService feignService;
    @Resource
    FeignHystrixService feignHystrixService;

    @GetMapping
    @ApiOperation("feign查询测试")
    public ResponseResult get() {
        return ResponseResult.succ(JSON.parseObject(feignService.testFeign()));
    }


    @GetMapping("hystrix")
    @ApiOperation("feign-熔断器-查询测试")
    public ResponseResult hystrix() {
        return ResponseResult.succ(JSON.parseObject(feignHystrixService.testHystrix()));
    }

}
