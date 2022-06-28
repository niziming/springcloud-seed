package cn.zm.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Description 暂无权限处理类
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler, Serializable {
  private static final long serialVersionUID = -7858869558953243875L;

  /**
   * 暂无权限返回结果
   * @Author Sans
   * @CreateTime 2019/10/3 8:41
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
    // ResultUtil.responseJson(response,ResultUtil.resultCode(403,"未授权"));
  }
}

