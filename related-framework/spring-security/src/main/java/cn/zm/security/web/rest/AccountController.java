package cn.zm.security.web.rest;

import cn.zm.security.web.entity.Account;
import cn.zm.security.web.entity.dto.AccountDTO;
import cn.zm.security.web.entity.vo.AccountVO;
import cn.zm.security.web.service.IAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.mq.plus.base.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.common.base.ResResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 账户表
 * @author 十渊
 * @since 2022-05-27
 */
@RequestMapping("account")
@RestController
@Api(tags = "账户表")
public class AccountController extends BaseController {

    @Resource
    private IAccountService accountService;

    @GetMapping
    @ApiOperation("账户表page查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序", dataTypeClass = boolean.class)
    // })
    public ResResult<IPage<AccountVO>> getByPage(AccountDTO account) {
        // TODO 分页查询
        IPage<AccountVO> page = accountService.selectByPage(getPage(), account);
        return ResResult.succ(page);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
    @GetMapping("list")
    @ApiOperation("账户表list查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序",  dataTypeClass = boolean.class)
    // })
    public ResResult<List<AccountVO>> list(@Validated AccountDTO account) {
        // TODO list
        List<Account> list = accountService.list(new QueryWrapper<>(account.convert()));
        List<AccountVO> vos = list.stream().map(entity -> {
            AccountVO vo = AccountVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
        return ResResult.succ(vos);
    }

    @GetMapping("{id}")
    @ApiOperation("账户表查询(id)")
    public ResResult<AccountVO> get(@PathVariable String id) {
        // TODO 查询
        Account entity = accountService.getById(id);
        AccountVO vo = AccountVO.builder().build();
        boolean b = Objects.nonNull(entity);
            BeanUtils.copyProperties(entity, vo);
        return ResResult.succ(b ? vo : null);
    }

    @PostMapping
    @ApiOperation("账户表新增")
    public ResResult add(@RequestBody @Validated AccountDTO account) {
        // TODO 新增
        Account entity = account.convert();
        boolean save = accountService.save(entity);
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("账户表删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        accountService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("账户表修改")
    public ResResult update(@RequestBody @Validated AccountDTO account) {
        // TODO 修改
        accountService.updateById(account.convert());
        return ResResult.succ("修改成功");
    }
}
