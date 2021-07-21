# 使用说明

## 快速开始
应用的启动类`com.alipay.demo.DemoApplication`位于`bootstrap`模块的`src/main/java`目录下，本地开发时直接运行该类的`main`方法即可启动应用

应用的集成测试基类`com.alipay.demo.AbstractTestBase`位于`bootstrap`或`test`模块的`src/test/java`目录下，继承该类的单元测试类可以集成SOFABoot应用启动

## SOFABoot 用户文档
你可以通过下述文档了解到 SOFABoot 的使用细节
+ SOFABoot 应用开发: [主站版SOFABoot应用开发概述](https://yuque.antfin-inc.com/middleware/sofaboot/db7fgl)
+ SOFABoot 版本: [主站版SOFABoot发布报告](https://yuque.antfin-inc.com/middleware/sofaboot/releasenote)
+ SOFABoot 技术支持: [主站版SOFABoot常见问题](https://yuque.antfin-inc.com/middleware/sofaboot/faq)

## 组件使用示例
下面是各个组件的使用示例代码,您可以找到它们,使用这些示例代码前请阅读类上的注释说明
* SOFABoot Web 示例代码:
    + `JsonSampleController`: RESTful服务示例代码
    + `VelocitySampleController`: Velocity模版渲染示例代码
* SOFA RPC 示例代码:
    + `RpcSampleController`: SOFA RPC 服务调用演示接口
* DRM 示例代码:
    + `DrmSampleController`: DRM 配置推送演示接口
    + `DrmSampleConfig`: DRM 配置注册示例代码
* AntScheduler 示例代码:
    + `SimpleServerlessTaskSample`: AntScheduler 简单任务示例代码
    + `ComplexServerlessTaskSample`: AntScheduler 三层分发任务示例代码
* MsgBroker 示例代码:
    + `MsgBrokerProducerClient`: MsgBroker 发送消息示例代码
    + `UniformEventMessageListenerImpl`: MsgBroker 接收消息示例代码
    + `MsgBrokerSampleController`: MsgBroker 发送/接收消息演示接口
* SOFAMQ 示例代码:
    + `SofaMQSampleController`: SofaMQ 发送/接收消息演示接口
    + `SofaMQSample`: SofaMQ 发送/接收消息示例代码
* ZDAL 示例代码:
    + `ZdalSampleController`: Zdal + JDBC 使用演示接口
* ZCache 示例代码:
    + `ZcacheConfiguration`: Zcache 客户端初始化示例代码
    + `ZcacheSampleController`: Zcache 客户端演示接口
* MyBatis Framework 示例代码:
    + `MyBatisConfiguration`: Mybatis 生成Mapper配置示例代码
    + `MybatisSampleController`: Mybatis 使用演示接口

## 组件用户文档
下面是各个组件件的用户文档,您可以查看详细的使用说明、工作原理:

* [SOFABoot Web Starter 用户文档](https://yuque.antfin-inc.com/middleware/sofaboot/rml0bg)
* [SOFA RPC 用户文档](https://yuque.antfin-inc.com/middleware/sofa-rpc)
* [DRM 用户文档](https://yuque.antfin-inc.com/middleware/drm)
* [AntScheduler 用户文档](https://yuque.antfin-inc.com/middleware/antscheduler)
* [MsgBroker 用户文档](https://yuque.antfin-inc.com/middleware/msgbroker)
* [SOFAMQ 用户文档](https://yuque.antfin-inc.com/middleware/sofamq/user_processes)
* [ZDAL 用户文档](https://yuque.antfin-inc.com/middleware/zdal)
* [ZCache 用户文档](https://yuque.antfin-inc.com/middleware/zcache)
* [Tracer 用户文档](https://yuque.antfin-inc.com/middleware/tracer)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

## 组件门户网站
下面是各个组件的门户网站,您可以进行问题咨询/意见反馈:

* [SOFA RPC 产品门户](https://sofa.alipay.com/product/sofa-rpc)
* [DRM 产品门户](https://sofa.alipay.com/product/drm)
* [AntScheduler 产品门户](https://sofa.alipay.com/product/scheduler)
* [MsgBroker 产品门户](https://sofa.alipay.com/product/msgbroker)
* [SOFAMQ 产品门户](https://yuque.antfin-inc.com/middleware/sofamq/preface)
* [ZDAL 产品门户](https://sofa.alipay.com/product/zdal)
* [ZCache 产品门户](https://sofa.alipay.com/product/zcache)
* [Tracer 产品门户](https://sofa.alipay.com/product/tracer)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

