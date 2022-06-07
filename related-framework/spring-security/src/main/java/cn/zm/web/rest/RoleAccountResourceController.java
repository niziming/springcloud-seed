package cn.zm.web.rest;

import cn.zm.web.service.IRoleAccountResourceService;
import cn.zm.web.entity.dto.RoleAccountResourceDTO;
import cn.zm.web.entity.vo.RoleAccountResourceVO;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.plus.base.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.common.base.ResResult;
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
 * VIEW
 * @author 十渊
 * @since 2022-05-30
 */
@RequestMapping("roleAccountResource")
@RestController
@Api(tags = "VIEW")
public class RoleAccountResourceController extends BaseController {

    // @Resource
    // private IRoleAccountResourceService roleAccountResourceService;
    //
    // @GetMapping
    // @ApiOperation("VIEWpage查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    // public ResResult<IPage<RoleAccountResourceVO>> getByPage(@Validated RoleAccountResourceDTO roleAccountResource) {
    //     // TODO 分页查询
    //     IPage<RoleAccountResourceVO> page = roleAccountResourceService.selectByPage(getPage(), roleAccountResource);
    //     return ResResult.succ(page);
    // }
    //
    // @PostMapping("list")
    // @ApiOperation("VIEWlist查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    // public ResResult<List<RoleAccountResourceVO>> list(@Validated @RequestBody RoleAccountResourceDTO roleAccountResource) {
    //     // TODO 分页查询
    //     IPage<RoleAccountResourceVO> page = roleAccountResourceService.list(getPage(), roleAccountResource);
    //     return ResResult.succ(page);
    // }
    //
    // @GetMapping("{id}")
    // @ApiOperation("VIEW查询(id)")
    // public ResResult<RoleAccountResourceVO> get(@PathVariable String id) {
    //     // TODO 查询
    //     boolean aNull = Objects.isNull(roleAccountResourceService.getById(id));
    //     return ResResult.succ(aNull ? null : roleAccountResourceService.getById(id).convert());
    // }
    //
    // @PostMapping
    // @ApiOperation("VIEW新增")
    // public ResResult add(@RequestBody @Validated RoleAccountResourceDTO roleAccountResource) {
    //     // TODO 新增
    //     roleAccountResourceService.save(roleAccountResource.convert());
    //     return ResResult.succ("新增成功");
    // }
    //
    // @DeleteMapping("{id}")
    // @ApiOperation("VIEW删除")
    // public ResResult del(@PathVariable String id) {
    //     // TODO 删除
    //     roleAccountResourceService.removeById(id);
    //     return ResResult.succ("删除成功");
    // }
    //
    // @PutMapping
    // @ApiOperation("VIEW修改")
    // public ResResult update(@RequestBody @Validated RoleAccountResourceDTO roleAccountResource) {
    //     // TODO 修改
    //     roleAccountResourceService.updateById(roleAccountResource.convert());
    //     return ResResult.succ("修改成功");
    // }
}
