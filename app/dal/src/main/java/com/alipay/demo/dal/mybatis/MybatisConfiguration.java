package com.alipay.demo.dal.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 演示多数据源场景的配置方式，使用前请先取消@Configuration注解的注释
 */
//@Configuration
@MapperScan(basePackages = "com.alipay.demo.dal.mybatis.mapper.single", sqlSessionFactoryRef = "sqlSessionFactoryBeanForSingle")
@MapperScan(basePackages = "com.alipay.demo.dal.mybatis.mapper.sharding", sqlSessionFactoryRef = "sqlSessionFactoryBeanForSharding")
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBeanForSingle(@Qualifier(value = "singleDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBeanForSharding(@Qualifier(value = "shardingDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
