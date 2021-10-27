package cn.zm.netflix.mybatisplus.web.rest;

import cn.zm.common.base.ResponseResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
// @RefreshScope 这个注解
@RefreshScope
@RequestMapping("test")
@RestController
@Api(tags = "plus-bus-测试接口")
public class TestController extends BaseController {

    @Resource
    private GlobalConfig globalConfig;

    @GetMapping
    @ApiOperation("获取全局配置")
    public ResponseResult getConfig() {
        // TODO 查询
        return ResponseResult.succ(globalConfig);
    }
}
