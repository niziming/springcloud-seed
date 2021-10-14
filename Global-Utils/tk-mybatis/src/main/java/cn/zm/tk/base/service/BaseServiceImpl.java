package cn.zm.tk.base.service;

import cn.zm.tk.anno.Like;
import cn.zm.tk.func.ListFunc;
import cn.zm.tk.base.mapper.BaseMapper;
import cn.zm.tk.utils.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/** 功能描述: <br>
 * <基础service>
 *
 * @author 倪子铭
 * @date 2021/10/13 16:33
 * @return
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Resource
    protected BaseMapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 条件查询
     *
     * @param record
     * @return
     */
    @Override
    public List<T> selectByProperty(T record) {
        return mapper.select(record);
    }

    /**
     * 条件查询单个 若存在多个则异常
     *
     * @param record
     * @return
     */
    @Override
    public T selectOneByProperty(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 通过主键 id 查询
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 通过主键 ids 查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Override
    public int save(T record) {
        return mapper.insert(record);
    }

    /**
     * 保存多个
     *
     * @param records
     * @return
     */
    @Override
    public int saveBatch(List<T> records) {
        return mapper.insertList(records);
    }

    /**
     * 存在字段保存
     *
     * @param record
     * @return
     */
    @Override
    public int saveSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    @Override
    public int updateById(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键删除
     *
     * @param record
     * @return
     */
    @Override
    public int deleteById(Object record) {
        // return mapper.delete(record);
        return mapper.deleteByPrimaryKey(record);
    }

    /**
     * 根据主键更新不为空的字段
     *
     * @param record
     * @return
     */
    @Override
    public int updateSelectiveById(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageBean<T> selectPageByProperty(T record, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = mapper.select(record);
        return new PageBean<T>(list);
    }

    @Override
    public PageBean<T> selectPageByFunc(ListFunc<T> func, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = func.list();
        return new PageBean<T>(list);
    }

    @Override
    public List<T> likeByProperty(T record) {
        Class<?> aClass = record.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Example example = new Example(aClass);
        Example.Criteria criteria = example.createCriteria();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Like like = field.getDeclaredAnnotation(Like.class);
                // if (ObjectUtils.isNotEmpty(field.get(record)) && field.get(record) instanceof String) {
                if (ObjectUtils.isNotEmpty(field.get(record))) {
                    if (ObjectUtils.isNotEmpty(like)) {
                        criteria.andLike(field.getName(),  "%"+field.get(record)+"%");
                    } else {
                        criteria.andEqualTo(field.getName(),  field.get(record));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mapper.selectByExample(example);
    }
}
