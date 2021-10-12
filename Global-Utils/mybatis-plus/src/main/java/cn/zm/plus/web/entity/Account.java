package cn.zm.plus.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import cn.zm.plus.config.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.zm.plus.web.entity.vo.AccountVO;

@Data
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="Account对象", description="用户表")
public class Account extends ObjectConvert<AccountVO>{
    @ApiModelProperty(value = "用户标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "余额")
    private Double money;
}

