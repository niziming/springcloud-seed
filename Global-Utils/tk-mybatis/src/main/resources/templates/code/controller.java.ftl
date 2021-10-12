package ${package.Controller};

import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.stream.Collectors;
import com.github.pagehelper.Page;
import com.xr.inspect.common.entity.Result;
import ${package.Entity}.dto.<#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity? cap_first}</#if>DTO;
import ${package.Entity}.vo.<#if entity?ends_with("VO")>${entity? cap_first? substring(0, entity? index_of("VO"))}<#else>${entity? cap_first}</#if>VO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;
import com.xr.inspect.data.utils.PageBean;
import java.util.List;
import com.xr.inspect.data.utils.ConvertUtil;


/**
 * ${table.comment!}
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RequestMapping("<#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>")
@RestController
@Api(tags = "${table.comment}接口")
<#else>
@Controller
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Resource
    private ${table.serviceName} <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service;

    @GetMapping
    @ApiOperation("${table.comment!}查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
        @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
        @ApiImplicitParam(name = "isDesc", value = "是否降序")
    })
    public Result<PageBean<<#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity? cap_first}</#if>VO>> page(@Validated <#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity}</#if>DTO <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTO</#if>) {
        // TODO 分页查询
        Page<<#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity? cap_first}</#if>VO> page = getPage();
        <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.selectByProperty(<#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTO</#if>.convert());
        return Result.ok(new PageBean(page));
    }

    @GetMapping("{id}")
    @ApiOperation("${table.comment!}查询(id)")
    public Result<<#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity? cap_first}</#if>VO> get(@PathVariable String id) {
        // TODO 查询
        return Result.ok(Objects.nonNull(<#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.selectById(id)) ? <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.selectById(id).convert() : null);
    }

    @PostMapping
    @ApiOperation("${table.comment!}新增(list)")
    public Result add(@RequestBody @Validated List<<#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity}</#if>DTO> <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTOs</#if>) {
        // TODO 新增
        <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTOs</#if> = <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTOs</#if>.stream().map(c -> c.setId(null)).collect(Collectors.toList());
        <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.saveBatch(ConvertUtil.convertList(<#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTOs</#if>));
        return Result.ok("新增成功");
    }

    @DeleteMapping("{id}")
    @ApiOperation("${table.comment!}删除")
    public Result del(@PathVariable String id) {
        // TODO 删除
        <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.deleteById(id);
        return Result.ok("删除成功");
    }

    @PutMapping
    @ApiOperation("${table.comment!}修改")
    public Result update(@RequestBody @Validated <#if entity?ends_with("DTO")>${entity? cap_first? substring(0, entity? index_of("DTO"))}<#else>${entity}</#if>DTO <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTO</#if>) {
        // TODO 修改
        <#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}</#if>Service.updateSelectiveById(<#if entity?ends_with("DTO")>${entity? substring(0, entity? uncap_first? index_of("DTO"))}<#else>${entity? uncap_first}DTO</#if>.convert());
        return Result.ok("修改成功");
    }
}
</#if>
