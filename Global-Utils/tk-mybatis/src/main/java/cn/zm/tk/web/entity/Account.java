package cn.zm.tk.web.entity;

import cn.zm.tk.anno.Like;
import cn.zm.tk.utils.ObjectConvert;
import cn.zm.tk.web.entity.vo.AccountVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "account")
@ApiModel(value="AccountDTO对象", description="用户表")
public class Account extends ObjectConvert<AccountVO> {
    @Id
    @ApiModelProperty(value = "用户标识")
    private Integer id;
    @Like
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "余额")
    private Double money;
}

