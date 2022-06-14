package cn.zm.sharding.sphere.mapper;

// OrderMapper.java

import cn.zm.sharding.sphere.dataobject.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
  Order selectById(@Param("id") Integer id);

  List<Order> selectListByUserId(@Param("userId") Integer userId);

  void insert(Order order);

}
