package cn.zm.config;

import cn.zm.filter.JwtRequestFilter;
import cn.zm.filter.PermissionsFilter;
import cn.zm.handler.*;
import cn.zm.security.UserAuthenticationProvider;
import cn.zm.security.UserPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;

  @Autowired
  private UserLoginSuccessHandler userLoginSuccessHandler;

  @Autowired
  private UserLogoutSuccessHandler userLogoutSuccessHandler;

  @Autowired
  private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;

  @Autowired
  private UserLoginFailureHandler userLoginFailureHandler;

  @Autowired
  private UserAuthenticationProvider userAuthenticationProvider;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private PermissionsFilter permissionsFilter;

  @Value("${jwt.ignore}")
  private String[] ignore;


  /**
   * http 相关的权限控制
   *
   * @param httpSecurity
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
      // 不进行权限验证的请求或资源(从配置文件中读取)
      .antMatchers(ignore).permitAll()
      // 其他的需要登陆后才能访问
      .anyRequest().authenticated()
      .and()
      // 配置未登录自定义处理类
      .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
      .and()
      // 配置登录地址
      .formLogin()
      .loginProcessingUrl("/authenticated")
      // 配置登录成功自定义处理类
      .successHandler(userLoginSuccessHandler)
      // 配置登录失败自定义处理类
      .failureHandler(userLoginFailureHandler)
      .and()
      // 配置登出地址
      .logout()
      .logoutUrl("/logout")
      // 配置用户登出自定义处理类
      .logoutSuccessHandler(userLogoutSuccessHandler)
      .and()
      // 配置没有权限自定义处理类
      .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
      .and()
      // 开启跨域
      .cors()
      .and()
      // 取消跨站请求伪造防护
      .csrf().disable();
    // 基于Token不需要session
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // 禁用缓存
    httpSecurity.headers().cacheControl();
    // 添加JWT过滤器
    // httpSecurity.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));
    // httpSecurity.addFilter(jwtRequestFilter);


    // // httpSecurity.authorizeRequests().antMatchers(ignore).permitAll()
    // //   .anyRequest().authenticated();
    //
    // // Add a filter to validate the tokens with every request
    //
    // // We don't need CSRF for this example
    // httpSecurity.csrf().disable()
    //   // dont authenticate this particular request
    //   .authorizeRequests()
    //   // .antMatchers(ignore)
    //   // .permitAll()
    //   // .antMatchers("/login", "/captchaImage").anonymous()
    //   .antMatchers("/emp/url").hasRole("emp")
    //   // all other requests need to be authenticated
    //   //其他的需要登陆后才能访问
    //   .anyRequest().authenticated()
    //   .and()
    //   //配置未登录自定义处理类
    //   .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
    //   .and()
    //   //配置登录地址
    //   .formLogin()
    //   .loginProcessingUrl("/jwt/authenticate")
    //   //配置登录成功自定义处理类
    //   .successHandler(userLoginSuccessHandler)
    //   //配置登录失败自定义处理类
    //   .failureHandler(userLoginFailureHandler)
    //   .permitAll()
    //   .and()
    //   //配置登出地址
    //   .logout()
    //   .logoutUrl("/jwt/authenticate/cancel")
    //   //配置用户登出自定义处理类
    //   .logoutSuccessHandler(userLogoutSuccessHandler)
    //   .and()
    //
    //   // make sure we use stateless session; session won't be used to
    //   // store user's state.
    //   // 配置没有权限自定义处理类
    //   .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler).and().sessionManagement()
    //   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    //
    // // Add a filter to validate the permissions with every request
    //
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    //
    // // httpSecurity.addFilter(permissionsFilter);
  }

  /**
   * web应用给相关的权限配置
   *
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    // super.configure(web);
    // 如果项目中不存在的地址也会被拦截
    // 不进行权限验证的请求或资源(从配置文件中读取)
    web.ignoring()
      // knif4j ignoring
      .antMatchers(ignore);
  }

  /**
   * 全局认证构建器
   *
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
    // auth.inMemoryAuthentication()
    auth.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder()) // 添加解密方式
    ;

  }

  /**
   * 配置登录验证逻辑
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth){
    //这里可启用我们自己的登陆验证逻辑
    auth.authenticationProvider(userAuthenticationProvider);
  }

  /**
   * 注入自定义PermissionEvaluator
   */
  @Bean
  public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler (@Autowired UserPermissionEvaluator u) {
    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setPermissionEvaluator(u);
    return handler;
  }



  /**
   * 加密方式
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}