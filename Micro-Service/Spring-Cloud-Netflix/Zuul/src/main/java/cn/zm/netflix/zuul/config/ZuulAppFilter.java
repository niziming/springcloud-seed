package cn.zm.netflix.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ZuulAppFilter extends ZuulFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /** 功能描述: <br>
     * <filterType：返回一个字符串代表过滤器的类型，
     * 在zuul中定义了四种不同生命周期的过滤器类型，具体如下：>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/25 10:17
     * @return java.lang.String
     */
    @Override
    public String filterType() {
        // pre：路由之前
        // routing：路由之时
        // post： 路由之后
        // error：发送错误调用
        return "pre";
    }

    /** 功能描述: <br>
     * <过滤的顺序>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/25 10:18
     * @return int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /** 功能描述: <br>
     * <这里可以写逻辑判断，是否要过滤，本文true,永远过滤。>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/25 10:18
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /** 功能描述: <br>
     * <过滤器的具体逻辑。可用很复杂，
     * 包括查sql，nosql去判断该请求到底有没有权限访问。>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/25 10:19
     * @return java.lang.Object
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        try {
            Assert.notNull(accessToken, "token is empty 401 err");
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
        // if(accessToken == null) {
        //     log.warn("token is empty");
        //     ctx.setSendZuulResponse(false);
        //     ctx.setResponseStatusCode(401);
        //     try {
        //         ctx.getResponse().getWriter().write("token is empty");
        //     }catch (Exception e){}
        //
        //     return null;
        // }
        log.info("ok");
        return null;
    }
}
