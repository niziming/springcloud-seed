package cn.zm.netflix.mybatisplus.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.netflix.mybatisplus.web.service.ServiceTkService;
import cn.zm.plus.base.BaseController;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("test")
@RestController
@Api(tags = "plus测试接口")
public class TestController extends BaseController {

    @Resource
    private GlobalConfig globalConfig;
    @Resource
    private ServiceTkService serviceTkService;

    @GetMapping
    @ApiOperation("获取全局配置")
    public ResResult getConfig() {
        // TODO 查询
        return ResResult.succ(globalConfig);
    }

    @GetMapping("getTkInfo")
    @ApiOperation("调用tk服务接口")
    public ResResult getTkInfo() {
        // TODO 查询
        return ResResult.succ(JSON.parse(serviceTkService.getTkInfo()));
    }
}
