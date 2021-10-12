package cn.zm.tk.utils;


import cn.zm.tk.func.ListFunc;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * @ClassName PageUtil
 * @Description 分页工具
 * @Author yeehaw
 * @Date 2020/2/25 14:42
 * @Version 1.0.0
 */
public class PageUtil {
    /**
     * lambda 分页查询工具
     * 此工具与 pageHelper 深度集成，
     *
     * @param func     lambda表达式 返回 list ，方法体内部必须存放 mapper查询
     * @param pageSize 页大小
     * @param pageNum  页码
     * @param <T>      返回参数类型
     * @return
     */
    public static <T> PageBean<T> page(ListFunc<T> func, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = func.list();
        return new PageBean<T>(list);
    }
}
