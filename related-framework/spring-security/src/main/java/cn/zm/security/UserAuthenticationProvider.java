package cn.zm.security;

import cn.zm.entity.SecurityAccount;
import cn.zm.service.JwtUserDetailsService;
import cn.zm.web.entity.Account;
import cn.zm.web.entity.RoleAccountResource;
import cn.zm.web.service.IAccountService;
import cn.zm.web.service.IRoleAccountResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录验证
 * @Author Sans
 * @CreateTime 2019/10/1 19:11
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private IRoleAccountResourceService roleAccountResourceService;
    @Autowired
    private IAccountService iAccountService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SecurityAccount securityAccount = jwtUserDetailsService.loadUserByUsername(userName);
        if (securityAccount == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, securityAccount.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (securityAccount.getStatus() != null && securityAccount.getStatus().equals("PROHIBIT")){
            throw new LockedException("LockedException");
        }

        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<RoleAccountResource> list = roleAccountResourceService.list(new QueryWrapper<>(RoleAccountResource.builder().username(userName).build()));
        for (RoleAccountResource roleAccountResource : list){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleAccountResource.getRoleName()));
        }
        securityAccount.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(securityAccount, password, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}