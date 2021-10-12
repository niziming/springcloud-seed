package cn.zm.tk.web.entity.dto;

import cn.zm.tk.utils.ObjectConvert;
import cn.zm.tk.web.entity.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@ApiModel(value="AccountDTO对象", description="用户表")
public class AccountDTO extends ObjectConvert<Account> {
    @Id
    @ApiModelProperty(value = "用户标识")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "余额")
    private Double money;
}

