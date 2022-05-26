package cn.zm.config;

import cn.zm.filter.JwtRequestFilter;
import cn.zm.filter.PermissionsFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private UserDetailsService jwtUserDetailsService;

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
    // httpSecurity.authorizeRequests().antMatchers(ignore).permitAll()
    //   .anyRequest().authenticated();

    // Add a filter to validate the tokens with every request

    // We don't need CSRF for this example
    httpSecurity.csrf().disable()
      // dont authenticate this particular request
      .authorizeRequests()
      // .antMatchers(ignore)
      // .permitAll()
      .antMatchers("/admin/url").hasRole("admin")
      .antMatchers("/emp/url").hasRole("emp")
      // all other requests need to be authenticated
      .anyRequest().authenticated().and()
      // make sure we use stateless session; session won't be used to
      // store user's state.
      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the permissions with every request

    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    // httpSecurity.addFilter(permissionsFilter);
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
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder())
    ;
  }

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