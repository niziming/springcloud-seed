package cn.zm.security.web.rest;

import cn.zm.security.web.entity.dto.RoleDTO;
import cn.zm.security.web.entity.vo.RoleVO;
import cn.zm.security.web.service.IRoleService;
import cn.zm.security.web.entity.Role;
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
 * 角色表
 * @author 十渊
 * @since 2022-05-27
 */
@RequestMapping("role")
@RestController
@Api(tags = "角色表")
public class RoleController extends BaseController {

    @Resource
    private IRoleService roleService;

    @GetMapping
    @ApiOperation("角色表page查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序", dataTypeClass = String.class)
    // })
    public ResResult<IPage<RoleVO>> getByPage(@Validated RoleDTO role) {
        // TODO 分页查询
        IPage<RoleVO> page = roleService.selectByPage(getPage(), role);
        return ResResult.succ(page);
    }

    @PostMapping("list")
    @ApiOperation("角色表list查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataTypeClass = String.class),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序", dataTypeClass = String.class)
    // })
    public ResResult<IPage<RoleVO>> list(@Validated RoleDTO role) {
        // TODO 分页查询
        IPage<RoleVO> page = roleService.selectByPage(getPage(), role);
        return ResResult.succ(page);
    }

    @GetMapping("{id}")
    @ApiOperation("角色表查询(id)")
    public ResResult<RoleVO> get(@PathVariable String id) {
      // TODO 查询
      Role entity = roleService.getById(id);
      RoleVO vo = RoleVO.builder().build();
      boolean b = Objects.nonNull(entity);
      BeanUtils.copyProperties(entity, vo);
      return ResResult.succ(b ? vo : null);
    }

    @PostMapping
    @ApiOperation("角色表新增")
    public ResResult<RoleVO> add(@RequestBody @Validated RoleDTO role) {
        // TODO 新增
        Role entity = role.convert();
        roleService.save(entity);
        RoleVO vo = RoleVO.builder().build();
        BeanUtils.copyProperties(entity, vo);
        return ResResult.succ(vo);
    }

    @DeleteMapping("{id}")
    @ApiOperation("角色表删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        roleService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("角色表修改")
    public ResResult update(@RequestBody @Validated RoleDTO role) {
        // TODO 修改
        roleService.updateById(role.convert());
        return ResResult.succ("修改成功");
    }
}
