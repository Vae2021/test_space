package com.alipay.demo.service.scheduler;

import com.alipay.antschedulerclient.serverless.model.ServerlessSimpleJobExecuteContext;
import com.alipay.antschedulerclient.serverless.result.ServerlessCommonResult;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Func;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Ingress;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用简单定时任务任务，示例文档: https://yuque.antfin-inc.com/middleware/antscheduler/gdf8t3
 * <p> 在 http://antscheduler.stable.alipay.net/index.html#/overview?current=1&apps=&prioritys=&groupIds=&types=&name=&mode=serverless&recycle=disable 创建/触发定时任务
 */
@Scheduler(name = "SIMPLE_SERVERLESS_TASK")
@Component
public class SimpleServerlessTaskSample {
    private static final Logger log = LoggerFactory.getLogger(SimpleServerlessTaskSample.class);

    @Ingress
    @Func
    public ServerlessCommonResult execute(ServerlessSimpleJobExecuteContext context) {
        //在这里添加您的业务逻辑
        log.info("简单任务执行成功!");
        //失败时调用.buildFailResult("false");
        return ServerlessCommonResult.buildSuccessResult();
    }
}
