package cn.zm.netflix.hystrix.web.service;

public interface HystrixService {
    /** 功能描述: <br>
     * <ribbon方式的熔断处理>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/22 9:49
     * @return java.lang.String
     */
    String ribbonHystrix();

    /** 功能描述: <br>
     * <熔断处理信息>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/22 9:50
     * @return java.lang.String
     */
    String hystrixInfo();
}
