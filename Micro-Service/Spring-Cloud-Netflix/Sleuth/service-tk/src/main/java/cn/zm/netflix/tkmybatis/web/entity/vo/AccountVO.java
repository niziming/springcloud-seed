package cn.zm.netflix.tkmybatis.web.entity.vo;

import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "account")
@ApiModel(value="AccountVO对象", description="")
public class AccountVO {
    @ApiModelProperty(value = "用户主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private String niconame;
    private String username;
}

