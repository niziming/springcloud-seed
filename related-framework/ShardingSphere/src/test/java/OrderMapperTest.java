// OrderConfigMapperTest.java

import cn.zm.sharding.sphere.Application;
import cn.zm.sharding.sphere.dataobject.Order;
import cn.zm.sharding.sphere.dataobject.OrderConfig;
import cn.zm.sharding.sphere.mapper.OrderConfigMapper;
import cn.zm.sharding.sphere.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderMapperTest {


  @Autowired
  private OrderMapper orderMapper;

  @Test
  public void testSelectById() {
    Order order = orderMapper.selectById(1);
    System.out.println("order = " + order);
  }

  @Test
  public void testSelectListByUserId() {
    List<Order> orders = orderMapper.selectListByUserId(1);
    System.out.println(orders.size());
  }

  @Test
  public void testInsert() {
    Order order = new Order();
    order.setUserId(2);
    orderMapper.insert(order);
  }

}
