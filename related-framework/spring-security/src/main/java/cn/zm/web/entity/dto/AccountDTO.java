package cn.zm.web.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.zm.plus.utils.ObjectConvert;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.web.entity.Account;
import org.hibernate.validator.constraints.NotBlank;


@Data
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="AccountDTO对象", description="账户表")
public class AccountDTO extends ObjectConvert<Account>{
    @ApiModelProperty(value = "账户标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "账户")
    private String username;
    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;
}

