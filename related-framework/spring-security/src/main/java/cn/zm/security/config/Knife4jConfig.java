package cn.zm.security.config;

import cn.zm.knife4j.config.Knife4jConfiguration;
import cn.zm.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import springfox.documentation.builders.*;
import springfox.documentation.schema.Example;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jermaine
 */
@Slf4j
@Configuration
@EnableSwagger2
public class Knife4jConfig {

  @Autowired
  JwtTokenUtil jwtTokenUtil;
  @Autowired
  UserDetailsService userDetailsService;

  @Value("${jwt.admin}")
  private String tokenUser;

  @Bean(value = "defaultApi2")
  public Docket defaultApi2() {
    // public Docket defaultApi2(@Qualifier(value="knife4j") Docket docket){

    UserDetails userDetails = userDetailsService.loadUserByUsername(tokenUser);
    String token = jwtTokenUtil.generateToken(userDetails);
    log.info("token: {}","Bearer "+token);
    Docket docket = Knife4jConfiguration.defaultApi2();
    List<RequestParameter> requestParameterList = new ArrayList<>();
    RequestParameterBuilder requestParameterBuilder = new RequestParameterBuilder();
    RequestParameter authorization = requestParameterBuilder
      .name("Authorization")
      .description("权限")
      .in(ParameterType.HEADER)
      .required(false)
      // .content()
      .query(builder -> builder.defaultValue("Bearer "+token).allowEmptyValue(true))
      .example(new Example("1", "权限","权限", "Bearer "+token, "Bearer "+token,"1"))
      .build();
    RequestParameter demo = requestParameterBuilder
      .name("demo")
      .description("demo")
      .in(ParameterType.HEADER)
      .required(false)
      .query(builder -> builder.defaultValue("Bearer "+token).allowEmptyValue(true))
      .example(new ExampleBuilder().value("zh1234").build())
      .build();
    requestParameterList.add(authorization);
    requestParameterList.add(demo);
    docket.globalRequestParameters(requestParameterList);
    return docket;
  }
}
