package com.alipay.demo.service.zcache;

import com.alipay.zcache.impl.RefreshableCommonTbaseCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化一个 {@link RefreshableCommonTbaseCacheManager} 用于操作 Gzone TBase，使用前先取消 @Configuration 注解的注释
 * <p> 参考文档: https://yuque.antfin.com/tbase-docs/guide/co3v9g
 */
//@Configuration
public class ZcacheConfiguration {
    @Value("${spring.application.name}")
    private String appName;

    @Bean
    RefreshableCommonTbaseCacheManager demoCacheManager() throws Exception {
        RefreshableCommonTbaseCacheManager refreshableCommonTbaseCacheManager = new RefreshableCommonTbaseCacheManager();
        // 应用名称
        refreshableCommonTbaseCacheManager.setAppName(appName);
        // 实际使用时需要替换成配置管控平台提交申请时填写的【应用Tbase租户名称】
        refreshableCommonTbaseCacheManager.setAppTairName("sofabootdemoTBaseCache");
        // 配置管控地址，根据实际使用的环境进行替换
        refreshableCommonTbaseCacheManager.setZdalConsoleUrl("http://zdataconsole-pool.stable.alipay.net:8080");
        // 设置访问tbase的超时时间
        refreshableCommonTbaseCacheManager.setTimeout(2000);
        refreshableCommonTbaseCacheManager.init();
        return refreshableCommonTbaseCacheManager;
    }
}
