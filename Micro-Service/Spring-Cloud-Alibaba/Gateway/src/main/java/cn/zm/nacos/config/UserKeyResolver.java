package cn.zm.nacos.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 功能描述: <br>
 * <以用户的维度去限流>
 *
 * @author 十渊
 * @date 2021/11/18 15:35
 * @return
 */
public class UserKeyResolver implements KeyResolver {
  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    return Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
  }
}
