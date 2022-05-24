package cn.zm.netflix.mybatisplus.web.mapper;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


public interface TestMapper extends BaseMapper {
  @Select("select COLUMN_TYPE " +
    "from information_schema.columns " +
    "where TABLE_SCHEMA = 'testdb' and COLUMN_NAME = 'type';")
  Object selectEnum();
}
