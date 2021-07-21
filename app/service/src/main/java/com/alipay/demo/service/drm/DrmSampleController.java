package com.alipay.demo.service.drm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本接口用于在演示DRM的基本使用方式，在DRM控制台推送数据后，通过该接口可以看到DRM控制台中推送的数据
 */
@RestController
@RequestMapping("/drm")
public class DrmSampleController {

    @Autowired
    private DrmSampleConfig drmSampleConfig;

    @GetMapping
    public String helloDrm() {
        return drmSampleConfig.getGreetingWord();
    }
}