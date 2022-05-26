package cn.zm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {


  /**
   * web 请求配置
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    // super.configure(web);
    web.ignoring().antMatchers("/resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // super.configure(http);
    http.authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
      .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
      .antMatchers("/addNewEmployee").hasAnyRole("ADMIN")
      .anyRequest().authenticated().and()
      .formLogin().loginPage("/login").permitAll().and()
      .logout().logoutUrl("/logout").permitAll()
    ;

    http.csrf().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // super.configure(auth);
    auth.inMemoryAuthentication()
      .withUser("employee").password("employee").authorities("ROLE_USER").and()
      .withUser("jermaine").password("jermaine").authorities("ROLE_USER", "ROLE_ADMIN");
  }

}
