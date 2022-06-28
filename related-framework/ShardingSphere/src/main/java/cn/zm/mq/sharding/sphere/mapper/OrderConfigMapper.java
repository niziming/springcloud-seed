package cn.zm.mq.sharding.sphere.mapper;
// OrderConfigMapper.java

import cn.zm.mq.sharding.sphere.dataobject.OrderConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderConfigMapper {
  OrderConfig selectById(@Param("id") Integer id);
}
