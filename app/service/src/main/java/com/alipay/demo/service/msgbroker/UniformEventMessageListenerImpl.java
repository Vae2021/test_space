package com.alipay.demo.service.msgbroker;

import com.alipay.common.event.UniformEvent;
import com.alipay.common.event.UniformEventContext;
import com.alipay.common.event.UniformEventMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用 msgbroker 订阅一条简单消息，示例文档:https://yuque.antfin-inc.com/middleware/msgbroker/vt7qry
 * <p> consumer 的定义在 msgbroker.xml 文件中, 需要取消其中的注释
 */
@Component("uniformEventMessageListener")
public class UniformEventMessageListenerImpl implements UniformEventMessageListener {

    private static final Logger logger = LoggerFactory
            .getLogger(UniformEventMessageListenerImpl.class);

    @Override
    public void onUniformEvent(UniformEvent uniformEvent, UniformEventContext uniformEventContext)
            throws Exception {
        /* Get TOPIC, EVENTCODE and payload from the received uniform event.*/
        final String topic = uniformEvent.getTopic();
        final String eventcode = uniformEvent.getEventCode();
        final Object payload = uniformEvent.getEventPayload();

        logger.info("[Receive a uniformEvent] topic [{}] eventcode [{}] eventId [{}] payload [{}]",
                topic, eventcode, uniformEvent.getId(), payload);
    }
}
