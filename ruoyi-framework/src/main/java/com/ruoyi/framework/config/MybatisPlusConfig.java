package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// proxyTargetClass = true 强制使用 CGLIB 代理，兼容若依的类结构
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 阻止恶意或误操作的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 分页插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        pageInterceptor.setMaxLimit(5000L);
        pageInterceptor.setOptimizeJoin(true);
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }
}
