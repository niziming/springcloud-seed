package cn.zm.netflix.mybatisplus.web.rest;

import cn.hutool.core.lang.Assert;
import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.netflix.mybatisplus.web.mapper.TestMapper;
import cn.zm.plus.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("test")
@RestController
@Api(tags = "plus测试接口")
public class TestController extends BaseController {

  @Resource
  private GlobalConfig globalConfig;

  @Resource
  private TestMapper testMapper;

  @GetMapping
  @ApiOperation("获取全局配置")
  public ResResult getConfig() {
    // TODO 查询
    return ResResult.succ(globalConfig);
  }

  @GetMapping("enum")
  @ApiOperation("返回一个枚举")
  public ResResult getEnum() {
    MyEnum red = MyEnum.RED;
    // TODO 查询
    return ResResult.succ(red);
  }


  @GetMapping("getEnumInDB")
  @ApiOperation("数据库查询一个枚举")
  public ResResult getEnumInDB() {
    String o = testMapper.selectEnum().toString();
    Assert.notBlank(o, "枚举查询为空");
    String substring = o.substring(o.indexOf("(") + 1, o.lastIndexOf(")"));
    String s = substring.replaceAll("'", "");
    String[] split = s.split(",");
    AtomicInteger index = new AtomicInteger(1);
    Map<Object, Object> collect = Arrays.stream(split).collect(
      Collectors.toMap(
        k -> {
          System.out.println("key = " + k);
          return Integer.toString(index.getAndIncrement());
        },
        v -> v
      )
    );
    // HashMap<Object, Object> collect = Arrays.stream(split).collect(
    //   HashMap::new,
    //   (hashMap, s1) -> {
    //     System.out.println("hashMap = " + hashMap);
    //     System.out.println("s1 = " + s1);
    //     hashMap.put(index.incrementAndGet(), s1);
    //   },
    //   (hashMap, hashMap2) -> {
    //     System.out.println("hashMap = " + hashMap);
    //     System.out.println("hashMap2 = " + hashMap2);
    //   }
    // );
    // TODO 查询
    return ResResult.succ(collect);
  }

  public static enum MyEnum {
    RED(1, "红灯"),
    GREEN(2, "绿灯"),
    YELLOW(3, "黄灯");
    private int code;
    private String status;

    MyEnum(int code, String status) {
      this.code = code;
      this.status = status;
    }

    MyEnum() {
      this.code = 1;
    }

    @Override
    public String toString() {
      return "MyEnum{" +
        "code=" + code +
        ", status='" + status + '\'' +
        '}';
    }
  }
}
