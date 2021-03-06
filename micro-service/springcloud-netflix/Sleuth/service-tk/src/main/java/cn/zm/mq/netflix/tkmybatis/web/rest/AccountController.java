package cn.zm.mq.netflix.tkmybatis.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.mq.netflix.tkmybatis.web.entity.Account;
import cn.zm.mq.netflix.tkmybatis.web.entity.dto.AccountDTO;
import cn.zm.mq.netflix.tkmybatis.web.entity.vo.AccountVO;
import cn.zm.mq.netflix.tkmybatis.web.mapper.AccountMapper;
import cn.zm.mq.netflix.tkmybatis.web.service.IAccountService;
import cn.zm.mq.tk.utils.ConvertUtil;
import cn.zm.mq.tk.utils.PageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.mq.tk.base.BaseController;
import java.util.stream.Collectors;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.List;


/**
 * 
 * @author 十渊
 * @since 2021-10-13
 */
@RequestMapping("account")
@RestController
@Api(tags = "接口")
public class AccountController extends BaseController {
    @Resource
    private IAccountService accountService;

    @Value("${server.port}")
    private String port;

    @Resource
    private AccountMapper accountMapper;

    @GetMapping
    @ApiOperation("查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
        @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
        @ApiImplicitParam(name = "isDesc", value = "是否降序")
    })
    public ResResult<PageResult<AccountVO>> page(@Validated AccountDTO accountDTO) {
        // TODO 分页查询
        Page<AccountVO> page = getPage();
        List<Account> accounts = accountService.likeByProperty(accountDTO.convert());
        return ResResult.succ(
            PageResult.build(Objects.isNull(page) ? accounts : page)
        );
    }

    @GetMapping("{id}")
    @ApiOperation("查询(id)")
    public ResResult<AccountVO> get(@PathVariable String id) {
        // TODO 查询
        return ResResult.succ(Objects.nonNull(accountService.selectById(id)) ? accountService.selectById(id).convert() : null);
    }

    @PostMapping
    @ApiOperation("新增(list)")
    public ResResult add(@RequestBody @Validated List<AccountDTO> accountDTOs) {
        // TODO 新增
        accountDTOs = accountDTOs.stream().map(c -> c.setId(null)).collect(Collectors.toList());
        accountService.saveBatch(ConvertUtil.convertList(accountDTOs));
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        accountService.deleteById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("修改")
    public ResResult update(@RequestBody @Validated AccountDTO accountDTO) {
        // TODO 修改
        accountService.updateSelectiveById(accountDTO.convert());
        return ResResult.succ("修改成功");
    }

    @GetMapping("ribbon/service")
    @ApiOperation("负载均衡测试")
    public ResResult ribbonService() {
        // TODO 负载均衡测试
        return ResResult.succ("tkapp:"+port);
    }
}
