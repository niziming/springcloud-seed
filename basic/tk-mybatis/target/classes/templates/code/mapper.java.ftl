package ${package.Mapper};

import ${package.Entity}.${entity};
import com.xr.inspect.data.base.mapper.BaseMapper;

<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    @Override
    default Class<?> entityClass () {return ${entity}.class;}
}
</#if>
