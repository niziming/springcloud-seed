package cn.zm.web.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.web.entity.BaseResource;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Accessors(chain = true)
@TableName("base_resource")
@ApiModel(value="BaseResourceDTO对象", description="系统资源表")
public class BaseResourceDTO extends ObjectConvert<BaseResource>{
    @ApiModelProperty(value = "系统资源标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @NotBlank
    @ApiModelProperty(value = "Api")
    private String apiUrl;
}

