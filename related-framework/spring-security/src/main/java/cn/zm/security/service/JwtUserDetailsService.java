package cn.zm.security.service;

import cn.zm.security.entity.SecurityAccount;
import cn.zm.security.web.entity.Account;
import cn.zm.security.web.service.IAccountService;
import cn.zm.security.web.service.IRoleAccountResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Value("${jwt.admin}")
  private String admin;

  @Resource
  private IRoleAccountResourceService roleAccountResourceService;

  @Resource
  private IAccountService accountService;

  // 这里可以查询数据库推入到userdetails中 可以将缓存写在此处 此处密码账户写死在配置中的
  @Override
  public SecurityAccount loadUserByUsername(String username) throws UsernameNotFoundException {
    SecurityAccount securityAccount = null;
    if (admin.equals(username)) { // 密码加密前为 password
      securityAccount = SecurityAccount.builder()
        .username(admin)
        .password("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6")
        .authorities(new ArrayList<>())
        .build();
    } else {
      Account account = accountService.getOne(new QueryWrapper<>(Account.builder().username(username).build()));
      if (null != account) {
        securityAccount = SecurityAccount.builder().build();
        BeanUtils.copyProperties(account, securityAccount);
      }
        // throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return securityAccount;
  }
}
