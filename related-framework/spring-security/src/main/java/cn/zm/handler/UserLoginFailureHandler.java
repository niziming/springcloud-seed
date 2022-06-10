package cn.zm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录失败处理类
 */
@Slf4j
@Component
// public class UserLoginFailureHandler implements AuthenticationFailureHandler {
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
  /**
   * 登录失败返回结果
   *
   * @Author Sans
   * @CreateTime 2019/10/3 9:12
   */
  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception
  ) throws IOException {
    // 这些对于操作的处理类可以根据不同异常进行不同处理
    if (exception instanceof UsernameNotFoundException)
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Username not found");
    if (exception instanceof LockedException)
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "User locked");
    if (exception instanceof BadCredentialsException)
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Bad credentials");
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Login failed");
  }
}

