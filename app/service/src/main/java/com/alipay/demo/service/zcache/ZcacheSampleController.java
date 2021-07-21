package com.alipay.demo.service.zcache;

import com.alipay.zcache.impl.RefreshableCommonTbaseCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 本接口用于演示ZCache的基本使用方式
 * <p> CacheManager在ZcacheConfiguration类中创建，请取消该类上的 @Configuration 注解上的注释
 */
@RestController
@RequestMapping("/zcache")
public class ZcacheSampleController {

    @Autowired(required = false)
    @Qualifier("demoCacheManager")
    private RefreshableCommonTbaseCacheManager cacheManager;

    @GetMapping
    public String helloZcache() {
        org.springframework.util.Assert.notNull(cacheManager, "请确保 ZcacheConfiguration 类中的 @Configuration 注释已打开");
        String key = UUID.randomUUID().toString();
        String value = "欢迎使用 ZCACHE";
        //插入一条KV
        String setResult = cacheManager.set(key, value);
        Assert.isTrue("OK".equals(setResult), "返回值为OK");

        //查询KV
        String getResult = (String) cacheManager.get(key);
        Assert.isTrue(value.equals(getResult), "返回值为写入值");

        //删除KV
        cacheManager.del(key);
        return getResult;
    }
}
