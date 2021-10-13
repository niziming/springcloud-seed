package cn.zm.netflix.tkmybatis.web.entity;

import javax.persistence.*;

import cn.zm.netflix.tkmybatis.web.entity.vo.AccountVO;
import cn.zm.tk.utils.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "account")
@ApiModel(value="Account对象", description="")
public class Account extends ObjectConvert<AccountVO> {
    @ApiModelProperty(value = "用户主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private String niconame;
    private String username;
    private String pwd;
}

