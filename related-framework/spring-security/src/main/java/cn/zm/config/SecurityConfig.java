// package cn.zm.config;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// /**
//  * web security 配置 适配器
//  * @author 十渊Jermaine
//  */
// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//   // @Autowired
//   // VerifyCodeFilter verifyCodeFilter;
//
//   /**
//    * 权限管理构建器
//    * @param auth
//    * @throws Exception
//    */
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     // super.configure(auth);
//
//     // 加密
//     BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//     String pwd = bCryptPasswordEncoder.encode("user");
//
//     // 添加一个admin用户,设置用户名及密码
//     auth.inMemoryAuthentication()
//       .withUser("user")
//       // .password("user")
//       .password(pwd)
//       .roles("admin");
//   }
//
//   @Bean
//   public PasswordEncoder passwordEncoder() {
//     return new BCryptPasswordEncoder();
//   }
//
//   /**
//    * 忽略拦截
//    * web security
//    * * 通配当前路径 ** 通配当前及子路径
//    * @param web
//    * @throws Exception
//    */
//   @Override
//   public void configure(WebSecurity web) throws Exception {
//     // super.configure(web);
//     // 如果项目中不存在的地址也会被拦截
//     web.ignoring().antMatchers("/security/not/*");
//   }
//
//   /**
//    *
//    * @param http
//    * @throws Exception
//    */
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//     super.configure(http);
//   }
//
//   @Bean
//   @Override
//   public AuthenticationManager authenticationManagerBean() throws Exception {
//     return super.authenticationManagerBean();
//   }
//
// }
