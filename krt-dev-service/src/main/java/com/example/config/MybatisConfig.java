package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.pojo.mapper")
public class MybatisConfig {
    /**
     * 新版
     */
    // @Bean
    // public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //     MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    //     // 乐观锁
    //     mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    //
    //     //分页插件
    //     PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
    //     paginationInnerInterceptor.setMaxLimit(10L);
    //     paginationInnerInterceptor.setDbType(DbType.MYSQL);
    //     paginationInnerInterceptor.setOptimizeJoin(true);
    //     mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
    //     return mybatisPlusInterceptor;
    // }
}
