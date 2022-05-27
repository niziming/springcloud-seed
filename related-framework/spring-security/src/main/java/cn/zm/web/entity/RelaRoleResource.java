package cn.zm.web.entity;

import cn.zm.web.entity.vo.RelaRoleResourceVO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("rela_role_resource")
@ApiModel(value="RelaRoleResource对象", description="角色系统资源关联表")
public class RelaRoleResource {
    @ApiModelProperty(value = "角色系统资源关联标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "资源名称")
    private Long roleId;
    @ApiModelProperty(value = "Api")
    private Long resourceId;
}

