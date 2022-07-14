package cn.zm.mq.tk.base;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/2/20 18:50
 * @description 基础控制器
 */
@Slf4j
public class BaseController {
    private final static String PAGE = "page";

    private final static String SIZE = "size";

    private final static String ORDER_BY_COLUMN = "orderByColumn";

    private final static String IS_DESC = "isDesc";

    /**
     * 分页查询
     *
     * @return 分页对象
     */
    protected <T> Page<T> getPage() {
        HttpServletRequest request = getRequest();
        log.debug("当前页数: [{}], 每页数量: [{}], 排序字段: [{}], 是否降序: [{}]",
                request.getParameter(PAGE),
                request.getParameter(SIZE),
                request.getParameter(ORDER_BY_COLUMN),
                request.getParameter(IS_DESC));
        Integer page = Convert.toInt(request.getParameter(PAGE));
        Integer size = Convert.toInt(request.getParameter(SIZE));
        String orderByColumn = Convert.toStr(request.getParameter(ORDER_BY_COLUMN));
        Boolean isDesc = Convert.toBool(request.getParameter(IS_DESC));
        if (Objects.isNull(page) || Objects.isNull(size)) {
            return null;
        }
        if (StringUtils.isNotBlank(orderByColumn)) {
            if (!Objects.isNull(isDesc)) {
                return PageHelper.startPage(page, size, orderByColumn + " " + (isDesc ? "asc" : "desc"));
            }
        }
        return PageHelper.startPage(page, size);
    }

    private ServletRequestAttributes getAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    private HttpServletRequest getRequest() {
        return getAttributes().getRequest();
    }

}
