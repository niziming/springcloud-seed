package cn.zm.security.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.security.web.entity.BaseResource;
import cn.zm.security.web.entity.dto.BaseResourceDTO;
import cn.zm.security.web.entity.vo.BaseResourceVO;
import cn.zm.security.web.service.IBaseResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.mq.plus.base.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统资源表
 * @author 十渊
 * @since 2022-05-27
 */
@RequestMapping("baseResource")
@RestController
@Api(tags = "系统资源表")
public class BaseResourceController extends BaseController {

    @Resource
    private IBaseResourceService baseResourceService;

    @GetMapping
    @ApiOperation("系统资源表page查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
    //     @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    public ResResult<IPage<BaseResourceVO>> getByPage(@Validated BaseResourceDTO baseResource) {
        // TODO 分页查询
        IPage<BaseResourceVO> page = baseResourceService.selectByPage(getPage(), baseResource);
        return ResResult.succ(page);
    }

    @PostMapping("list")
    @ApiOperation("系统资源表list查询")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    //     @ApiImplicitParam(name = "isDesc", value = "是否降序")
    // })
    public ResResult<List<BaseResourceVO>> list(@Validated @RequestBody BaseResourceDTO baseResource) {
        // TODO 分页查询
        List<BaseResource> list = baseResourceService.list(new QueryWrapper<>(baseResource.convert()));
        List<BaseResourceVO> vos = list.stream().map(entity -> {
            BaseResourceVO vo = BaseResourceVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
        return ResResult.succ(vos);
    }

    @GetMapping("{id}")
    @ApiOperation("系统资源表查询(id)")
    public ResResult<BaseResourceVO> get(@PathVariable String id) {
        // TODO 查询
        BaseResource entity = baseResourceService.getById(id);
        BaseResourceVO vo = BaseResourceVO.builder().build();
        boolean b = Objects.nonNull(entity);
        BeanUtils.copyProperties(entity, vo);
        return ResResult.succ(b ? vo : null);
    }

    @PostMapping
    @ApiOperation("系统资源表新增")
    public ResResult add(@RequestBody @Validated BaseResourceDTO baseResource) {
        // TODO 新增
        baseResourceService.save(baseResource.convert());
        return ResResult.succ("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("系统资源表删除")
    public ResResult del(@PathVariable String id) {
        // TODO 删除
        baseResourceService.removeById(id);
        return ResResult.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("系统资源表修改")
    public ResResult update(@RequestBody @Validated BaseResourceDTO baseResource) {
        // TODO 修改
        baseResourceService.updateById(baseResource.convert());
        return ResResult.succ("修改成功");
    }
}
