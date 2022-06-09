package cn.zm.web.rest;

import cn.zm.web.entity.RelaRoleResource;
import cn.zm.web.service.IRelaRoleResourceService;
import cn.zm.web.entity.dto.RelaRoleResourceDTO;
import cn.zm.web.entity.vo.RelaRoleResourceVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.plus.base.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.common.base.ResResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色系统资源关联表
 * @author 十渊
 * @since 2022-05-27
 */
@RequestMapping("relaRoleResource")
@RestController
@Api(tags = "角色系统资源关联表")
public class RelaRoleResourceController extends BaseController {

    @Resource
    private IRelaRoleResourceService relaRoleResourceService;

    @GetMapping
    @ApiOperation("角色系统资源关联表page查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    public ResResult<IPage<RelaRoleResourceVO>> getByPage(@Validated RelaRoleResourceDTO relaRoleResource) {
        // TODO 分页查询
        IPage<RelaRoleResourceVO> page = relaRoleResourceService.selectByPage(getPage(), relaRoleResource);
        return ResResult.succ(page);
    }

    @PostMapping("list")
    @ApiOperation("角色系统资源关联表list查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    public ResResult<List<RelaRoleResourceVO>> list(@Validated @RequestBody RelaRoleResourceDTO relaRoleResource) {

        // TODO list
        List<RelaRoleResource> list = relaRoleResourceService.list(new QueryWrapper<>(relaRoleResource.convert()));
        List<RelaRoleResourceVO> vos = list.stream().map(entity -> {
            RelaRoleResourceVO vo = RelaRoleResourceVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
        return ResResult.succ(vos);
    }

    @GetMapping("{id}")
    @ApiOperation("角色系统资源关联表查询(id)")
    public ResResult<RelaRoleResourceVO> get(@PathVariable String id) {
        // TODO 查询
        RelaRoleResource entity = relaRoleResourceService.getById(id);
        RelaRoleResourceVO vo = RelaRoleResourceVO.builder().build();
        boolean b = Objects.nonNull(entity);
        BeanUtils.copyProperties(entity, vo);
        return ResResult.succ(b ? vo : null);
    }

    @PostMapping
    @ApiOperation("角色系统资源关联表新增")
    public ResResult add(@RequestBody @Validated RelaRoleResourceDTO relaRoleResource) {
        // TODO 新增
        relaRoleResourceService.save(relaRoleResource.convert());
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("角色系统资源关联表删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        relaRoleResourceService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("角色系统资源关联表修改")
    public ResResult update(@RequestBody @Validated RelaRoleResourceDTO relaRoleResource) {
        // TODO 修改
        relaRoleResourceService.updateById(relaRoleResource.convert());
        return ResResult.succ("修改成功");
    }
}
