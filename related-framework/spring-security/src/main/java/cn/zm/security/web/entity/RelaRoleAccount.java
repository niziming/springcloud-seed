package cn.zm.security.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("rela_role_account")
@ApiModel(value="RelaRoleAccount对象", description="角色账户关联标表")
public class RelaRoleAccount {
    @ApiModelProperty(value = "角色账户关联标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "角色标识")
    private Long roleId;
    @ApiModelProperty(value = "账户标识")
    private Long accountId;
}

