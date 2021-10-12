package cn.zm.netflix.app.web.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import cn.zm.plus.utils.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.netflix.app.web.entity.Account;

@Data
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="AccountDTO对象", description="")
public class AccountDTO extends ObjectConvert<Account>{
    @ApiModelProperty(value = "用户主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String niconame;
    private String username;
    private String pwd;
}

