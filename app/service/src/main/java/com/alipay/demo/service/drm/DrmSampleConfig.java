package com.alipay.demo.service.drm;

import com.alipay.drm.client.DRMClient;
import com.alipay.drm.client.api.annotation.DAttribute;
import com.alipay.drm.client.api.annotation.DResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 通过 {@link DResource} 将普通的 bean 注册为 DRM 资源，使用文档：https://yuque.antfin-inc.com/middleware/drm/vsiqpl
 * 资源类对应的控制台页面地址: http://opssla.stable.alipay.net/drm/configAttribute.htm?id=537224528
 */
@Component
@DResource(id = "com.drmdemo.config")
public class DrmSampleConfig {

    /**
     * 定义一个 drm 属性，drm 属性 必须有对应的 setter、getter 方法，建议用 idea 自动生成的方式，不要手工写 setter、getter方法
     * 另外 drm 属性不能是静态属性，也就是不能是 static 属性，否则失效
     */
    @DAttribute
    private String greetingWord;


    /**
     * 为了方便演示，这里的应用名配置成了已创建好资源的应用
     * 实际编写代码时，应该通过 @Value("${spring.application.name}") 注入应用实际应用名
     */
    private String appName = "drm-demo";

    /**
     * 注册 drm 资源类
     * 只有注册了的 drm 资源类才能从 drm 服务端获取属性值以及监听属性变更事件
     */
    @PostConstruct
    public void init() {
        DRMClient.getInstance().register(this, appName);
    }

    /**
     * 属性对应的 getter 方法，drm 页面 "服务端列表" 中显示的值是调用此 getter 方法返回的值
     */
    public String getGreetingWord() {
        return greetingWord;
    }

    /**
     * 属性对应的 setter 方法，每次从 drm 服务端获取到属性值都会回调此方法来更新 bean 属性值。
     * 业务需要在属性值变更时，做一些回调方法，就在此方法中执行。
     * 注意：setter 方法不宜做耗时很长（超过3s）的操作，否则会导致 setter 方法一直重试调用，最佳实践是耗时长的操作单读开启一个线程执行。
     *
     * @param greetingWord 最新的属性值
     */
    public void setGreetingWord(String greetingWord) {
        this.greetingWord = greetingWord;
    }
}
