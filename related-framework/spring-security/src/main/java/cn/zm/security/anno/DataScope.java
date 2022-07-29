package cn.zm.security.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <数据权限>
 * @author 十渊Jermaine jermainenee@yeah.net
 * @version 1.0
 * @date 2022/7/29
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {
    /**
     * <什么范围>
     * @author 十渊Jermaine jermainenee@yeah.net
     * @version 1.0
     * @date 2022/7/29
    */
    String[] scopes();
    /**
     * <哪个字段>
     * @author 十渊Jermaine jermainenee@yeah.net
     * @version 1.0
     * @date 2022/7/29
    */
    String column() default "delete_status";
}
