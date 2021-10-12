package ${package.Service};

import ${package.Entity}.${entity};
import com.xr.inspect.data.base.service.BaseService;

<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends BaseService<${entity}> {
}
</#if>
