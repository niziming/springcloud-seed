package cn.zm.sharding.sphere.dataobject;

import lombok.Data;

/**
 * 订单 DO
 */
@Data
public class Order {

  /**
   * 订单编号
   */
  private Long id;
  /**
   * 用户编号
   */
  private Integer userId;

  // ... 省略 setting/getting 方法
}