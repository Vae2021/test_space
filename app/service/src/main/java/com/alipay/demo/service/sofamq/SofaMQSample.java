package com.alipay.demo.service.sofamq;

import com.alibaba.fastjson.JSON;
import com.alipay.sofa.sofamq.api.MessageConsumer;
import com.alipay.sofa.sofamq.api.MessageProducer;
import com.alipay.sofa.sofamq.api.Messaging;
import com.alipay.sofa.sofamq.api.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * SOFAMQ 使用示例，使用文档:https://yuque.antfin-inc.com/middleware/sofamq/quickstart_sofaboot#7l5w9
 */
@Component
@Messaging
public class SofaMQSample {
    private static final Logger log              = LoggerFactory.getLogger(SofaMQSample.class);

    // 这里配置的group和topic仅用作示例，请替换为应用自己申请的
    @MessageProducer(group = "GID_DEMO", topic = "TP_BYTES_DEMO", payload = byte[].class)
    public Producer<byte[]>    bytesProducer;

    // 通过字节数组发送普通消息
    public OrderPojo sendRawBytes() {
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setId(123);
        bytesProducer.send(bytesProducer.messageBuilder().withTags("TRADE").withValue(JSON.toJSONBytes(orderPojo)).build());
        log.info("[Send rawbytes:{}]", JSON.toJSONString(orderPojo));
        return orderPojo;
    }

    // 接收字节数组普通消息
    // 这里配置的group和topic仅用作示例，请替换为应用自己申请的
    @MessageConsumer(group = "GID_DEMO", topic = "TP_BYTES_DEMO")
    public void handleRawBytes(byte[] payload) {
        log.info("[Receive rawbytes:{}]" + new String(payload));
    }
}
