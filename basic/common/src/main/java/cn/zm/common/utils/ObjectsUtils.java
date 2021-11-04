package cn.zm.common.utils;

import java.util.Arrays;

/** 功能描述: <br>
 * <类 工具类>
 *
 * @author 十渊
 * @date 2021/11/4 9:15
 * @return
 */
public class ObjectsUtils {

  /** 功能描述: <br>
   * <源数组是否匹配所有目标>
   *
   * @param target
   * @param sources
   *
   * @author 十渊
   * @date 2021/11/4 9:07
   * @return boolean
   */
  public static boolean allMatch(Object target, Object ...sources) {
    return Arrays.stream(sources).allMatch(p -> p.equals(target));
  }

  /** 功能描述: <br>
   * <源数组中是否任意匹配目标>
   *
   * @param target
   * @param sources
   *
   * @author 十渊
   * @date 2021/11/4 9:08
   * @return boolean
   */
  public static boolean anyMatch(Object target, Object ...sources) {
    return Arrays.stream(sources).anyMatch(p -> p.equals(target));
  }

}
