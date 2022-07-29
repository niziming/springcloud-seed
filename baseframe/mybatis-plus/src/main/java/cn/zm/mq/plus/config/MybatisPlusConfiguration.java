package cn.zm.mq.plus.config;

// import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@EnableCaching(proxyTargetClass = true)
@MapperScan("cn.zm.**.mapper")
public class MybatisPlusConfiguration {
    /**
     * 分页插件
     */
    // @Bean
    // public PaginationInterceptor paginationInterceptor() {
    //     return new PaginationInterceptor();
    // }
    // @Bean
    // public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //     MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //
    //     // 数据权限
    //     interceptor.addInnerInterceptor(dataScopeInterceptor());
    //     // 分页插件
    //     interceptor.addInnerInterceptor(paginationInnerInterceptor());
    //     // 乐观锁插件
    //     interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
    //
    //     return interceptor;
    // }
    //
    // /**
    //  * 分页插件，自动识别数据库类型
    //  */
    // public PaginationInnerInterceptor paginationInnerInterceptor() {
    //     PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
    //     // 设置数据库类型为mysql
    //     paginationInnerInterceptor.setDbType(DbType.MYSQL);
    //     // 设置最大单页限制数量，默认 500 条，-1 不受限制
    //     paginationInnerInterceptor.setMaxLimit(-1L);
    //     return paginationInnerInterceptor;
    // }
    //
    // /**
    //  * 乐观锁插件
    //  */
    // public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
    //     return new OptimisticLockerInnerInterceptor();
    // }
    //
    // /**
    //  * 数据权限插件
    //  *
    //  * @return DataScopeInterceptor
    //  */
    // public DataScopeInterceptor dataScopeInterceptor() {
    //     return new DataScopeInterceptor();
    // }
}
