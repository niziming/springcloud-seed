package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.xr.inspect.data.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
<#if kotlin>
open class ${table.serviceImplName} : BaseServiceImpl<${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends BaseServiceImpl<${entity}> implements ${table.serviceName} {
}
</#if>
