package cn.zm.mq.netflix.mybatisplus.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;


public interface TestMapper extends BaseMapper {
  @Select("select COLUMN_TYPE " +
    "from information_schema.columns " +
    "where TABLE_SCHEMA = 'testdb' and COLUMN_NAME = 'type';")
  Object selectEnum();
}
