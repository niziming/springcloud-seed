package cn.zm.security.aspect;

import cn.zm.security.anno.DataScope;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <自定义数据权限切面>
 * @author 十渊Jermaine jermainenee@yeah.net
 * @version 1.0
 * @date 2022/7/29
*/
@Aspect
@Slf4j
@Component
public class DataScopeAspect {

        /**
         * 通过ThreadLocal记录权限相关的属性值
         */
        public static ThreadLocal<DataScope> threadLocal = new ThreadLocal<>();

        /**
         * 清空当前线程上次保存的权限信息
         */
        @After("dataScopePointCut()")
        public void clearThreadLocal(){
                threadLocal.remove();
                log.debug("threadLocal.remove()");
        }

        /**
         * 注解对象
         * 该成员变量在并发情况下导致多个线程公用了一套 @DataScope 配置的参数，导致sql拼接失败，必须改为局部变量
         * 修改于：2022.04.28
         */
        // private DataScopeParam controllerDataScope;

        /**
         * 配置织入点
         */
        @Pointcut("@annotation(cn.zm.security.anno.DataScope)")
        public void dataScopePointCut() {}

        @Before("dataScopePointCut()")
        public void doBefore(JoinPoint point) {
                // 获得注解
                DataScope dataScope = getAnnotationLog(point);
                if (dataScope != null) {
                        threadLocal.set(dataScope);
                        // 获取当前的用户及相关属性，需提前获取和保存数据权限对应的部门ID集合
                        // User currentUser = SecurityUtil.getUser();
                        // DataScopeParam dataScopeParam = new DataScopeParam(controllerDataScope.getDeptAlias()(),
                        //         controllerDataScope.getDeptField()(),
                        //         currentUser.isAdmin(),
                        //         currentUser.getDataScope());
                        // threadLocal.set(dataScopeParam);
                        // log.debug("currentUser.getDataScope() = {}", currentUser.getDataScope());
                }
        }

        //环绕通知
        @Around("dataScopePointCut()")
        public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
                System.out.println("环绕通知前");
                proceedingJoinPoint.proceed();//被增强的方法执行
                System.out.println("环绕通知后");
        }

        /**
         * 是否存在注解，如果存在就获取
         */
        private DataScope getAnnotationLog(JoinPoint joinPoint) {
                Signature signature = joinPoint.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                if (method != null) {
                        return method.getAnnotation(DataScope.class);
                }
                return null;
        }

}
