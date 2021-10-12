package cn.zm.plus.web.rest;

import cn.zm.plus.web.service.IAccountService;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.plus.config.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.common.common.ResponseResult;
import cn.zm.plus.web.entity.dto.AccountDTO;
import cn.zm.plus.web.entity.vo.AccountVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * 用户表
 * @author jermaine
 * @since 2021-06-27
 */
@RequestMapping("account")
@RestController
@Api(tags = "用户表")
public class AccountController extends BaseController {

    @Resource
    private IAccountService accountService;

    @GetMapping
    @ApiOperation("用户表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
        @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
        @ApiImplicitParam(name = "isDesc", value = "是否降序")
    })
    public ResponseResult<IPage<AccountVO>> getByPage(@Validated AccountDTO account) {
        // TODO 分页查询
        IPage<AccountVO> page = accountService.selectByPage(getPage(), account);
        return ResponseResult.succ(page);
    }

    @GetMapping("{id}")
    @ApiOperation("用户表查询(id)")
    public ResponseResult<AccountVO> get(@PathVariable String id) {
        // TODO 查询
        boolean aNull = Objects.isNull(accountService.getById(id));
        return ResponseResult.succ(aNull ? null : accountService.getById(id).convert());
    }

    @PostMapping
    @ApiOperation("用户表新增")
    public ResponseResult add(@RequestBody @Validated AccountDTO account) {
        // TODO 新增
        accountService.save(account.convert());
        return ResponseResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("用户表删除")
    public ResponseResult del(@PathVariable String id) {
        // TODO 删除
        accountService.removeById(id);
        return ResponseResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("用户表修改")
    public ResponseResult update(@RequestBody @Validated AccountDTO account) {
        // TODO 修改
        accountService.updateById(account.convert());
        return ResponseResult.succ("修改成功");
    }
}
