package cn.zm.rest;

import cn.zm.entity.JwtRequest;
import cn.zm.web.entity.RoleAccountResource;
import cn.zm.entity.JwtResponse;
import cn.zm.service.JwtUserDetailsService;
import cn.zm.util.JwtTokenUtil;
import cn.zm.web.entity.Role;
import cn.zm.web.service.IRoleAccountResourceService;
import cn.zm.web.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "认证接口")
@RestController
@RequestMapping("jwt")
@CrossOrigin
public class JwtAuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService jwtuserDetailsService;

  @Autowired
  private IRoleService roleService;

  @Resource
  private IRoleAccountResourceService roleAccountResourceService;

  @ApiOperation("认证")
  @PostMapping("/authenticate")
  public ResponseEntity<JwtResponse> createAuthenticationToken(
    @RequestBody JwtRequest authenticationRequest,
    HttpServletResponse response
    ) {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtuserDetailsService
      .loadUserByUsername(authenticationRequest.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  // authenticate 认证
  private void authenticate(String username, String password)  {
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
      authenticationManager.authenticate(authenticationToken);
    } catch (DisabledException e) {
      throw new DisabledException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("INVALID_CREDENTIALS", e);
    }
  }

}
