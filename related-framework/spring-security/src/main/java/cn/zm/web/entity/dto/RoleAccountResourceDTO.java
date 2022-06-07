package cn.zm.web.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.zm.plus.utils.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.web.entity.RoleAccountResource;

@Data
@Accessors(chain = true)
@TableName("role_account_resource")
@ApiModel(value="RoleAccountResourceDTO对象", description="VIEW")
public class RoleAccountResourceDTO extends ObjectConvert<RoleAccountResource>{
    @ApiModelProperty(value = "账户标识")
    private Long accountId;
    @ApiModelProperty(value = "账户")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "角色标识")
    private Long roleId;
    @ApiModelProperty(value = "角色名称")
    private String rolename;
    @ApiModelProperty(value = "系统资源标识")
    private Long resourceId;
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @ApiModelProperty(value = "Api")
    private String apiUrl;
}

