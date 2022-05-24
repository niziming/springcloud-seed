package cn.zm.netflix.tkmybatis.web.entity.dto;

import javax.persistence.*;

import cn.zm.netflix.tkmybatis.web.entity.Account;
import cn.zm.tk.utils.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "account")
@ApiModel(value="AccountDTO对象", description="")
public class AccountDTO extends ObjectConvert<Account> {
    @ApiModelProperty(value = "用户主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private String niconame;
    private String username;
    private String pwd;
}

