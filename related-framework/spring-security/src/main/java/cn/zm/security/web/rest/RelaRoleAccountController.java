package cn.zm.security.web.rest;

import cn.zm.security.web.entity.dto.RelaRoleAccountDTO;
import cn.zm.security.web.entity.vo.RelaRoleAccountVO;
import cn.zm.security.web.service.IRelaRoleAccountService;
import cn.zm.security.web.entity.RelaRoleAccount;
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
import java.util.Objects;

/**
 * 角色账户关联标表
 * @author 十渊
 * @since 2022-05-27
 */
@RequestMapping("relaRoleAccount")
@RestController
@Api(tags = "角色账户关联标表")
public class RelaRoleAccountController extends BaseController {

    @Resource
    private IRelaRoleAccountService relaRoleAccountService;

    @GetMapping
    @ApiOperation("角色账户关联标表page查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序", dataTypeClass = String.class)
    // })
    public ResResult<IPage<RelaRoleAccountVO>> getByPage(@Validated RelaRoleAccountDTO relaRoleAccount) {
        // TODO 分页查询
        IPage<RelaRoleAccountVO> page = relaRoleAccountService.selectByPage(getPage(), relaRoleAccount);
        return ResResult.succ(page);
    }

    @PostMapping("list")
    @ApiOperation("角色账户关联标表list查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序", dataTypeClass = String.class)
    // })
    public ResResult<IPage<RelaRoleAccountVO>> list(@Validated RelaRoleAccountDTO relaRoleAccount) {
        // TODO 分页查询
        IPage<RelaRoleAccountVO> page = relaRoleAccountService.selectByPage(getPage(), relaRoleAccount);
        return ResResult.succ(page);
    }

    @GetMapping("{id}")
    @ApiOperation("角色账户关联标表查询(id)")
    public ResResult<RelaRoleAccountVO> get(@PathVariable String id) {
        // TODO 查询
        RelaRoleAccount entity = relaRoleAccountService.getById(id);
        RelaRoleAccountVO vo = RelaRoleAccountVO.builder().build();
        boolean b = Objects.nonNull(entity);
        BeanUtils.copyProperties(entity, vo);
        return ResResult.succ(b ? vo : null);
    }

    @PostMapping
    @ApiOperation("角色账户关联标表新增")
    public ResResult add(@RequestBody @Validated RelaRoleAccountDTO relaRoleAccount) {
        // TODO 新增
        relaRoleAccountService.save(relaRoleAccount.convert());
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("角色账户关联标表删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        relaRoleAccountService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("角色账户关联标表修改")
    public ResResult update(@RequestBody @Validated RelaRoleAccountDTO relaRoleAccount) {
        // TODO 修改
        relaRoleAccountService.updateById(relaRoleAccount.convert());
        return ResResult.succ("修改成功");
    }
}
