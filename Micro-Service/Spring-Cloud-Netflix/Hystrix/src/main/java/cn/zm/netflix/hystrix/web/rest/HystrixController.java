package cn.zm.netflix.hystrix.web.rest;

import cn.zm.common.base.ResponseResult;
import cn.zm.netflix.hystrix.web.service.HystrixService;
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
@RequestMapping("hystix")
@RestController
@Api(tags = "Hystix-熔断测试接口")
public class HystrixController {
    @Resource
    HystrixService ribbonService;

    @GetMapping
    @ApiOperation("查询测试")
    public ResponseResult get() {
        return ResponseResult.succ(JSON.parse(ribbonService.ribbonHystrix()));
    }

}
