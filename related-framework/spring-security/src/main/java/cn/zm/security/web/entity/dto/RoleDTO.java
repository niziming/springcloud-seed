package cn.zm.security.web.entity.dto;

import cn.zm.security.web.entity.Role;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.mq.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Accessors(chain = true)
@TableName("role")
@ApiModel(value="RoleDTO对象", description="角色表")
public class RoleDTO extends ObjectConvert<Role>{
    @ApiModelProperty(value = "角色标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}

