package cn.zm.mq.tk.utils;

import java.util.List;
import java.util.stream.Collectors;

/** 功能描述: <br>
 * <转换工具>
 *
 * @author 十渊
 * @date 2021/10/15 14:11
 * @return
 */
public class ConvertUtil {
    /**
     * 获取 vo 分页数据
     *
     * @param page do 分页数据
     * @return vo 分页数据
     */
    public static <T, E extends ObjectConvert> List<T> convertList(List<E> page) {
        return page.stream().map(e -> (T)e.convert()).collect(Collectors.toList());
    }

}
