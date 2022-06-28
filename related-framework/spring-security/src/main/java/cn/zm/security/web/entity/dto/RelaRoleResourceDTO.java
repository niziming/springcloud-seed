package cn.zm.security.web.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.mq.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.security.web.entity.RelaRoleResource;

@Data
@Accessors(chain = true)
@TableName("rela_role_resource")
@ApiModel(value="RelaRoleResourceDTO对象", description="角色系统资源关联表")
public class RelaRoleResourceDTO extends ObjectConvert<RelaRoleResource>{
    @ApiModelProperty(value = "角色系统资源关联标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "资源名称")
    private Long roleId;
    @ApiModelProperty(value = "Api")
    private Long resourceId;
}

