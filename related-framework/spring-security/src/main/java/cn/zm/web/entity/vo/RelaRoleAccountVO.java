package cn.zm.web.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("rela_role_account")
@ApiModel(value="RelaRoleAccountVO对象", description="角色账户关联标表")
public class RelaRoleAccountVO {
    @ApiModelProperty(value = "角色账户关联标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "角色标识")
    private Long roleId;
    @ApiModelProperty(value = "账户标识")
    private Long accountId;
}

