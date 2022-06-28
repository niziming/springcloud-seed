package cn.zm.mq.async.rest;

import cn.zm.mq.async.service.CompositeService;
import cn.zm.common.base.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;

@RequestMapping("async")
@RestController
@Api(tags = "异步编程")
public class AsycController {
    @Resource
    private CompositeService compositeService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @GetMapping("search")
    @ApiOperation("搜索")
    public ResResult searchList() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> submit = threadPoolTaskExecutor.submit(() -> 1);
        return ResResult.succ(submit.get());
    }
}
