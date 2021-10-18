package cn.zm.netflix.tkmybatis.web.rest;

import cn.hutool.core.util.ArrayUtil;
import cn.zm.common.base.ResponseResult;
import cn.zm.netflix.tkmybatis.web.entity.Account;
import cn.zm.netflix.tkmybatis.web.entity.dto.AccountDTO;
import cn.zm.netflix.tkmybatis.web.entity.vo.AccountVO;
import cn.zm.netflix.tkmybatis.web.mapper.AccountMapper;
import cn.zm.netflix.tkmybatis.web.service.IAccountService;
import cn.zm.tk.utils.ConvertUtil;
import cn.zm.tk.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.tk.base.BaseController;
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
    @Autowired
    @Qualifier("accountServiceImpl")
    // @Resource
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
    public ResponseResult<PageResult<AccountVO>> page(@Validated AccountDTO accountDTO) {
        // TODO 分页查询
        Page<AccountVO> page = getPage();
        List<Account> accounts = accountService.likeByProperty(accountDTO.convert());
        return ResponseResult.succ(
            PageResult.build(Objects.isNull(page) ? accounts : page)
        );
    }

    @GetMapping("{id}")
    @ApiOperation("查询(id)")
    public ResponseResult<AccountVO> get(@PathVariable String id) {
        // TODO 查询
        return ResponseResult.succ(Objects.nonNull(accountService.selectById(id)) ? accountService.selectById(id).convert() : null);
    }

    @PostMapping
    @ApiOperation("新增(list)")
    public ResponseResult add(@RequestBody @Validated List<AccountDTO> accountDTOs) {
        // TODO 新增
        accountDTOs = accountDTOs.stream().map(c -> c.setId(null)).collect(Collectors.toList());
        accountService.saveBatch(ConvertUtil.convertList(accountDTOs));
        return ResponseResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public ResponseResult del(@PathVariable String id) {
        // TODO 删除
        accountService.deleteById(id);
        return ResponseResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseResult update(@RequestBody @Validated AccountDTO accountDTO) {
        // TODO 修改
        accountService.updateSelectiveById(accountDTO.convert());
        return ResponseResult.succ("修改成功");
    }

    @GetMapping("ribbon/service")
    @ApiOperation("负载均衡测试")
    public ResponseResult ribbonService() {
        // TODO 负载均衡测试
        return ResponseResult.succ("tkapp:"+port);
    }
}
