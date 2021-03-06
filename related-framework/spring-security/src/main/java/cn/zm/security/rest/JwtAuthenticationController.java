package cn.zm.security.rest;

import cn.hutool.core.lang.Assert;
import cn.zm.security.entity.JwtRequest;
import cn.zm.security.service.JwtUserDetailsService;
import cn.zm.security.web.entity.Account;
import cn.zm.security.entity.JwtResponse;
import cn.zm.security.util.JwtTokenUtil;
import cn.zm.security.web.entity.dto.AccountDTO;
import cn.zm.security.web.entity.vo.AccountVO;
import cn.zm.security.web.service.IAccountService;
import cn.zm.security.web.service.IRoleAccountResourceService;
import cn.zm.security.web.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

  @Resource
  private IAccountService accountService;

  @Value("${jwt.token.prefix}")
  private String prefix;

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

    return ResponseEntity.ok(new JwtResponse(prefix+token));
  }

  @ApiOperation("注册")
  @PostMapping("/register")
  public ResponseEntity<AccountVO> register(
    @RequestBody @Validated AccountDTO accountDTO
  ) {
    Account account = Account.builder().username(accountDTO.getUsername()).build();
    List<Account> list = accountService.list(new QueryWrapper<>(account));
    Assert.isTrue(list.size() == 0, "此用户名已注册");

    account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));

    accountService.save(account);
    AccountVO vo = AccountVO.builder().build();
    BeanUtils.copyProperties(account, vo);

    return ResponseEntity.ok(vo);
  }

  // authenticate 认证
  private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
    // try {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    authenticationManager.authenticate(authenticationToken);
    // } catch (DisabledException e) {
    //   throw new DisabledException("USER_DISABLED", e);
    // } catch (BadCredentialsException e) {
    //   throw new BadCredentialsException("INVALID_CREDENTIALS", e);
    // }
  }

}
