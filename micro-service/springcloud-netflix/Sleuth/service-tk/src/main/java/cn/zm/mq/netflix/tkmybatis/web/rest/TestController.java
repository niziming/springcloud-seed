package cn.zm.mq.netflix.tkmybatis.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.mq.netflix.tkmybatis.web.service.PlusService;
import cn.zm.mq.tk.base.BaseController;
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
@RequestMapping("test")
@RestController
@Api(tags = "tk测试接口")
public class TestController extends BaseController {

    @Resource
    private GlobalConfig globalConfig;
    @Resource
    private PlusService plusService;

    @GetMapping
    @ApiOperation("获取全局配置")
    public ResResult getConfig() {
        // TODO 查询
        return ResResult.succ(globalConfig);
    }

    @GetMapping("getPlusInfo")
    @ApiOperation("调用Plus服务接口")
    public ResResult getPlusInfo() {
        // TODO 查询
        String plusInfo = plusService.getPlusInfo();
        return ResResult.succ(JSON.parse(plusInfo));
    }
}
