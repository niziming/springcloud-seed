package cn.zm.mq.plus.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <分页工具>
 * @author 十渊Jermaine niziming@mabangerp.com
 * @version 1.0
 * @date 2022/7/28
*/
@Slf4j
public class Pager {
    private final static String PAGE = "page";

    private final static String SIZE = "size";

    private final static String ORDER_BY_COLUMN = "orderByColumn";

    private final static String IS_DESC = "isDesc";


    /**
     * <DTO 入参查询 分页 返回VO>
     * @author 十渊Jermaine jermainenee@yeah.net
     * @version 1.0
     * @date 2022/7/29
    */
    public static <E, T> IPage<E> getPageDTO2VO(BaseMapper baseMapper, Wrapper wrapper, Class<E> eClass) {
        IPage<T> page = getPage();
        IPage<T> entityPage = baseMapper.selectPage(page, wrapper);
        IPage<E> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        IPage<E> iPage = pageViews.setRecords(entityPage.getRecords().stream().map(e -> {
            E vo = null;
            try {
                vo = eClass.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            BeanUtils.copyProperties(e, vo);
            return vo;
        }).collect(Collectors.toList()));
        return iPage;
    }

    /**
     * <截取http的分页排序参数>
     * @author 十渊Jermaine jermainenee@yeah.net
     * @version 1.0
     * @date 2022/7/29
    */
    public static <T> IPage<T> getPage() {
        HttpServletRequest request = getRequest();
        log.debug("当前页数: [{}], 每页数量: [{}], 排序字段: [{}], 是否降序: [{}]",
                request.getParameter(PAGE),
                request.getParameter(SIZE),
                request.getParameter(ORDER_BY_COLUMN),
                request.getParameter(IS_DESC));
        Integer page = Convert.toInt(request.getParameter(PAGE)) == null ? 1 : Convert.toInt(request.getParameter(PAGE));
        Integer size = Convert.toInt(request.getParameter(SIZE)) == null ? 10 : Convert.toInt(request.getParameter(SIZE));
        String orderByColumn = Convert.toStr(request.getParameter(ORDER_BY_COLUMN));
        Boolean isDesc = Convert.toBool(request.getParameter(IS_DESC));

        Page<T> iPage = new Page<>(page, size);
        if (StringUtils.isNotBlank(orderByColumn)) {
            OrderItem orderItem = Objects.isNull(isDesc) ?
                    OrderItem.asc(orderByColumn) :
                    isDesc ? OrderItem.desc(orderByColumn) :
                            OrderItem.asc(orderByColumn);
            iPage.addOrder(orderItem);
        }
        return iPage;
    }

    private static ServletRequestAttributes getAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    private static HttpServletRequest getRequest() {
        return getAttributes().getRequest();
    }

}
