package com.alipay.demo.service.scheduler;

import com.alipay.antschedulerclient.serverless.model.ServerlessClusterJobExecuteContext;
import com.alipay.antschedulerclient.serverless.result.ServerlessCommonResult;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Func;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Ingress;
import com.alipay.sofa.serverless.runtime.api.event.annotation.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用三层分发的定时任务，示例文档: https://yuque.antfin-inc.com/middleware/antscheduler/gdf8t3
 * <p> 在 http://antscheduler.stable.alipay.net/index.html#/overview?current=1&apps=&prioritys=&groupIds=&types=&name=&mode=serverless&recycle=disable 创建/触发定时任务
 */
@Scheduler(name = "COMPLEX_SERVERLESS_TASK")
@Component
public class ComplexServerlessTaskSample {
    private static final Logger log = LoggerFactory.getLogger(ComplexServerlessTaskSample.class);

    // 注意：SplitItem 建议是仅包含基本数据类型(JDK 内置的类型如 String)和「默认构造函数」/get/set/ 方法的模型
    @Ingress
    @Func(next = "load")
    public List<SplitItem> split(ServerlessClusterJobExecuteContext context) {
        SplitItem item1 = new SplitItem();
        item1.setId("1");
        List<SplitItem> splitItems = new ArrayList<>();
        splitItems.add(item1);
        return splitItems;
    }
    // 注意：SplitItemChunk 建议是仅包含基本数据类型(JDK 内置的类型如 String)和「默认构造函数」/get/set 方法的模型
    @Func(next = "execute")
    public List<SplitItemChunk> load(ServerlessClusterJobExecuteContext<SplitItem> context) {
        SplitItem splitItem = context.getChunk();
        List<SplitItemChunk> splitItemChunks = new ArrayList<>();
        SplitItemChunk splitItemChunk = new SplitItemChunk();
        splitItemChunk.setName(splitItem.getId());
        splitItemChunks.add(splitItemChunk);
        return splitItemChunks;
    }

    @Func
    public ServerlessCommonResult execute(ServerlessClusterJobExecuteContext<SplitItemChunk> context) {
        SplitItemChunk splitItemChunk = context.getChunk();
        log.info("三层分发任务执行成功, 分片结果:{}", splitItemChunk.getName());
        //失败时调用.buildFailResult("false");
        return ServerlessCommonResult.buildSuccessResult();
    }

    public static class SplitItem {

        String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class SplitItemChunk {

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
