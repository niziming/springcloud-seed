package cn.zm.security.security;

import cn.zm.security.web.entity.Account;
import cn.zm.security.web.entity.RoleAccountResource;
import cn.zm.security.web.service.IRoleAccountResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限认证工具
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
  @Autowired
  IRoleAccountResourceService roleAccountResourceService;

  /**
   * hasPermission鉴权方法
   * 这里仅仅判断PreAuthorize注解中的权限表达式
   * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
   * 当然targetUrl不一定是URL可以是数据Id还可以是管理员标识等,这里根据需求自行设计
   * @Author Sans
   * @CreateTime 2019/10/6 18:25
   * @Param  authentication  用户身份(在使用hasPermission表达式时Authentication参数默认会自动带上)
   * @Param  targetUrl  请求路径
   * @Param  permission 请求路径权限
   * @Return boolean 是否通过
   */
  @Override
  public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
    // 获取用户信息
    Account account =(Account) authentication.getPrincipal();
    // 查询用户权限(这里可以将权限放入缓存中提升效率)
    Set<String> permissions = new HashSet<>();
    List<RoleAccountResource> roleAccountResources = roleAccountResourceService.list(new QueryWrapper<>(RoleAccountResource.builder().accountId(account.getId()).build()));
    for (RoleAccountResource roleAccountResource : roleAccountResources) {
      permissions.add(roleAccountResource.getApiUrl());
    }
    // 权限对比
    if (permissions.contains(permission.toString())){
      return true;
    }
    return false;
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    return authorities.contains(permission.toString());
  }
}
