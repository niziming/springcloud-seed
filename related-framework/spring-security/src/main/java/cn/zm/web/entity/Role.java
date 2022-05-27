package cn.zm.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.web.entity.vo.RoleVO;

@Data
@Accessors(chain = true)
@TableName("role")
@ApiModel(value="Role对象", description="角色表")
public class Role {
    @ApiModelProperty(value = "角色标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String rolename;
}

