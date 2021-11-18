package cn.zm.gateway.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/** 功能描述: <br>
 * <比如根据Hostname进行限流>
 *
 * @author 十渊
 * @date 2021/11/18 15:28
 * @return
 */
public class HostAddrKeyResolver implements KeyResolver {
  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
  }
}
