package cn.zm.mq.tk.base.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * SelectByIds
 *
 * @author yeehaw
 * @Date 2020/12/28 8:46
 * @Description
 */
@RegisterMapper
public interface SelectByIdsMapper<T> {

    /**
     * 根据ids查询
     *
     * @param list
     * @return
     */
    @SelectProvider(type = SelectByIdsProvider.class, method = "dynamicSQL")
    List<T> selectByIds(List<Long> list);


}
