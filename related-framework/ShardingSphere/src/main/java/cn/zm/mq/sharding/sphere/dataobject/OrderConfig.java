package cn.zm.mq.sharding.sphere.dataobject;

import lombok.Data;

/**
 * 订单配置 DO
 */
@Data
public class OrderConfig {

  /**
   * 编号
   */
  private Integer id;
  /**
   * 支付超时时间
   *
   * 单位：分钟
   */
  private Integer payTimeout;

  // ... 省略 setting/getting 方法
}