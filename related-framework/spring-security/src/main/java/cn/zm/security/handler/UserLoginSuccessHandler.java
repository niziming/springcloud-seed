package cn.zm.security.handler;

import cn.zm.security.entity.SecurityAccount;
import cn.zm.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录成功处理类
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  JwtTokenUtil jwtTokenUtil;
  @Autowired
  UserDetailsService userDetailsService;
  @Value("${jwt.token.prefix}")
  String prefix;
  /**
   * 登录成功返回结果
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    // 组装JWT
    SecurityAccount securityAccount = (SecurityAccount) authentication.getPrincipal();
    String username = securityAccount.getUsername();
    // String token = JWTTokenUtil.createAccessToken(selfUserEntity);
    // token = JWTConfig.tokenPrefix + token;

    // String username = request.getParameter("username");
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    String token = jwtTokenUtil.generateToken(userDetails);

    // 封装返回参数
    // Map<String,Object> resultData = new HashMap<>();
    // resultData.put("code","200");
    // resultData.put("msg", "登录成功");
    // resultData.put("token",token);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write(prefix+token);
  }
}
