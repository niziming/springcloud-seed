package cn.zm.mq.netflix.config.web.rest;

import cn.zm.common.base.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("config")
@RestController
@Api(tags = "config-测试接口")
public class ConfigController {
    @GetMapping
    @ApiOperation("查询测试")
    public ResResult get() {
        return ResResult.succ("config-测试接口");
    }

}
