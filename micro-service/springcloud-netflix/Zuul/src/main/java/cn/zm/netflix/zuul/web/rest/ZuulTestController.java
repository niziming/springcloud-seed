package cn.zm.netflix.zuul.web.rest;

import cn.zm.common.base.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zuul")
@Api(tags = "网关接口测试")
public class ZuulTestController {
    @Autowired
    RouteLocator routeLocator;

    @ApiOperation("获取网关路由列表")
    @GetMapping
    public ResResult<List<Route>> getRouters() {
        return ResResult.succ(routeLocator.getRoutes());
    }
}
