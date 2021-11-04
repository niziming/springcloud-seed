// package cn.zm.gateway.config;
//
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * 功能描述: <br>
//  * <路由配置>
//  *
//  * @author 十渊
//  * @date 2021/11/2 16:18
//  * @return
//  */
// @Configuration
// public class RouteConfig {
//   private final String URL = "http://httpbin.org";
//   private final String URL1 = "http://www.baidu.com";
//   private final String URL2 = "/error";
//   private final String FORWARD = "/gateway/fallback";
//
//   @Bean
//   public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
//     return builder.routes()
//       .route(p -> p
//         .path("/get")
//         .filters(f -> f.addRequestHeader("Hello", "World"))
//         .uri(URL))
//       .route(p -> p
//         // .host("*.hystrix.com")
//         .path("/err")
//         .filters(f -> f
//           .hystrix(config -> config
//             .setName("mycmd")
//             .setFallbackUri("forward:/fallback")))
//         .uri(URL2))
//       .build();
//   }
// }
