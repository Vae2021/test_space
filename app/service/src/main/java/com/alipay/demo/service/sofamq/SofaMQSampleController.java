package com.alipay.demo.service.sofamq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本接口用于演示SOFAMQ的的基本使用方式:发送/接收一条消息
 * <p> 发送成功后在控制台中可以看到 [Send rawbytes:{}]
 * <p> 接收成功后在控制台中可以看到 [Receive rawbytes:{}]
 * <p> 使用默认参数时，由于消息可能被其它示例应用接收，不一定保证能接收到消息，可以替换成自己的topic和group以确保接收到
 */
@RestController
@RequestMapping("/sofamq")
public class SofaMQSampleController {
    @Autowired
    private SofaMQSample sofaMqSample;

    @GetMapping
    public OrderPojo helloSofaMq() {
        return sofaMqSample.sendRawBytes();
    }
}
