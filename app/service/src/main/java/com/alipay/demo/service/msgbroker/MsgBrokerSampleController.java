package com.alipay.demo.service.msgbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本接口用于演示MsgBroker的基本使用方式:发送/接收一条消息
 * <p> 发送成功后在控制台中可以看到 [Public a uniformEvent, success]
 * <p> 接收成功后在控制台中可以看到 [Receive a uniformEvent]
 * <p> 使用默认参数时，由于消息可能被其它示例应用接收，不一定保证能接收到消息，请替换成自己的消息类型以确保接收到
 */
@RestController
@RequestMapping("/msgbroker")
public class MsgBrokerSampleController {
    @Autowired
    private MsgBrokerProducerClient msgBrokerProducerClient;

    @GetMapping
    public Account helloMsgBroker() {
        return msgBrokerProducerClient.publishUniformEvent();
    }
}
