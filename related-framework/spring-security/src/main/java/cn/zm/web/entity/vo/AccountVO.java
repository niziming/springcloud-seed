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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="AccountVO对象", description="账户表")
public class AccountVO {
    @ApiModelProperty(value = "账户标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "账户")
    private String username;
    // @ApiModelProperty(value = "密码")
    // private String password;
}

