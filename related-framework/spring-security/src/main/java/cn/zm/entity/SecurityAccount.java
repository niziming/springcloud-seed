package cn.zm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="Account对象", description="账户表")
public class SecurityAccount implements UserDetails {
    @ApiModelProperty(value = "账户标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "账户")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    @ApiModelProperty(value = "账户状态")
    private String status;
    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 账户是否过期
     */
    private boolean isAccountNonExpired = false;
    /**
     * 账户是否被锁定
     */
    private boolean isAccountNonLocked = false;
    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;
    /**
     * 账户是否有效
     */
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

