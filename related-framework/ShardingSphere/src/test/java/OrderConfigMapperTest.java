// OrderConfigMapperTest.java

import cn.zm.sharding.sphere.Application;
import cn.zm.sharding.sphere.dataobject.OrderConfig;
import cn.zm.sharding.sphere.mapper.OrderConfigMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderConfigMapperTest {


  @Autowired
  private OrderConfigMapper orderConfigMapper;

  @Test
  public void testSelectById() {
    OrderConfig orderConfig = orderConfigMapper.selectById(1);
    System.out.println(orderConfig);
  }

}
