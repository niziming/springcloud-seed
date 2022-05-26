package cn.zm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Value("${jwt.admin}")
  private String admin;

  // 这里可以查询数据库推入到userdetails中 可以将缓存写在此处 此处密码账户写死在配置中的
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (admin.equals(username)) { // 密码加密前为 password
      return new User(
        admin,
        "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
        new ArrayList<>()
      );
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
