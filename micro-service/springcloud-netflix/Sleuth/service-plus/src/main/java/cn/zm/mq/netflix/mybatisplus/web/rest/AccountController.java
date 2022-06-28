package cn.zm.mq.netflix.mybatisplus.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.mq.netflix.mybatisplus.web.entity.dto.AccountDTO;
import cn.zm.mq.netflix.mybatisplus.web.entity.vo.AccountVO;
import cn.zm.mq.netflix.mybatisplus.web.service.IAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.mq.plus.base.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("account")
@RestController
@Api(tags = "用户表")
public class AccountController extends BaseController {

    @Resource
    private IAccountService accountService;

    @Value("${server.port}")
    private String port;

    @GetMapping
    @ApiOperation("查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
        @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
        @ApiImplicitParam(name = "isDesc", value = "是否降序")
    })
    public ResResult<IPage<AccountVO>> getByPage(@Validated AccountDTO account) {
        // TODO 分页查询
        IPage<AccountVO> page = accountService.selectByPage(getPage(), account);
        return ResResult.succ(page);
    }

    @GetMapping("{id}")
    @ApiOperation("查询(id)")
    public ResResult<AccountVO> get(@PathVariable String id) {
        // TODO 查询
        boolean aNull = Objects.isNull(accountService.getById(id));
        return ResResult.succ(aNull ? null : accountService.getById(id).convert());
    }

    @PostMapping
    @ApiOperation("新增")
    public ResResult add(@RequestBody @Validated AccountDTO account) {
        // TODO 新增
        accountService.save(account.convert());
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        accountService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("修改")
    public ResResult update(@RequestBody @Validated AccountDTO account) {
        // TODO 修改
        accountService.updateById(account.convert());
        return ResResult.succ("修改成功");
    }

    @GetMapping("ribbon/service")
    @ApiOperation("负载均衡测试")
    public ResResult ribbonService() {
        // TODO 负载均衡测试
        return ResResult.succ("mybatis-plus-app:"+port);
    }


}
