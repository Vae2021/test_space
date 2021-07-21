package com.alipay.demo.service.msgbroker;

import com.alipay.common.event.UniformEvent;
import com.alipay.common.event.UniformEventBuilder;
import com.alipay.common.event.UniformEventPublisher;
import com.alipay.common.event.impl.DefaultUniformEventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 使用 msgbroker 发布一条简单消息，示例文档: https://yuque.antfin-inc.com/middleware/msgbroker/uswbqg
 * <p> producer 的定义在 msgbroker.xml 文件中, 需要取消其中的注释
 */
@Component
public class MsgBrokerProducerClient {

    private static final Logger   logger              = LoggerFactory.getLogger(MsgBrokerProducerClient.class);

    private final static String   TOPIC               = "TP_DEFAULT_UNIFORM_EVENT";

    private final static String   EVENT_CODE           = "EC_TUTORIAL_TOM";

    @Autowired
    private UniformEventPublisher publisher;

    private final UniformEventBuilder   uniformEventBuilder = new DefaultUniformEventBuilder();

    public Account publishUniformEvent() {

        /* Create a UniformEvent instance. */
        final UniformEvent uniformEvent = uniformEventBuilder.buildUniformEvent(TOPIC, EVENT_CODE);

        /* Set the domain instance as event payload. */
        Account account = getDefaultAccount();
        uniformEvent.setEventPayload(account);

        /* Mark that a runtime exception must be thrown when publishing failure. */
        uniformEvent.setThrowExceptionOnFailed(true);

        try {
            publisher.publishUniformEvent(uniformEvent);

            logger.info(
                    "[Public a uniformEvent, success] TOPIC [{}] EVENTCODE [{}] eventId [{}] payload [{}]",
                    TOPIC, EVENT_CODE, uniformEvent.getId(),
                    uniformEvent.getEventPayload());
        } catch (Exception e) {
            logger.error(
                    "[Public a uniformEvent, failure] TOPIC [{}] EVENTCODE [{}] eventId [{}] error [{}]",
                    TOPIC, EVENT_CODE, uniformEvent.getId(), e.getMessage());
        }
        return account;
    }

    private Account getDefaultAccount() {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setAmount(new Random().nextDouble());
        account.setGmtCreate(new Date());
        return account;
    }
}
