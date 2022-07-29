package cn.zm.security.config;

import cn.zm.security.aspect.DataScopeAspect;
import cn.zm.security.handler.DataScopePermissionHandler;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * < Mybatisplus配置>
 * @author 十渊Jermaine jermainenee@yeah.net
 * @version 1.0
 * @date 2022/7/29
*/
@Configuration
@MapperScan("cn.zm.**.mapper*")
public class MybatisPlusConfig {

    @Autowired
    private DataScopePermissionHandler dataScopeAspect;

    /**
     * 最新版分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 添加数据权限插件
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        // 添加自定义的数据权限处理器
        dataPermissionInterceptor.setDataPermissionHandler(dataScopeAspect);
        interceptor.addInnerInterceptor(dataPermissionInterceptor);

        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
