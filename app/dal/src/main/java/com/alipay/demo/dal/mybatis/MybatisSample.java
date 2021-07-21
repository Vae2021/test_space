package com.alipay.demo.dal.mybatis;

import com.alipay.demo.dal.mybatis.mapper.sharding.ShardingStudentMapper;
import com.alipay.demo.dal.mybatis.mapper.single.SingleStudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Mybatis 使用教程
 */
@Component
public class MybatisSample {
    private static final Logger log = LoggerFactory.getLogger(MybatisSample.class);
    private final String number = UUID.randomUUID().toString();
    @Autowired(required = false)
    private SingleStudentMapper singleStudentMapper;

    @Autowired(required = false)
    private ShardingStudentMapper shardingStudentMapper;

    @Transactional(transactionManager = "txManagerForSingle")
    public StudentDO singleDataSourceTransactionTest() throws Exception {
        log.info("------------- 单库单表数据源事务内操作演示 ---------------");

        StudentDO studentDO = new StudentDO();
        studentDO.setNumber(number);
        studentDO.setName("Rose");
        studentDO.setScore("99");
        singleStudentMapper.insert(studentDO);

        log.info("单库单表数据源事务内插入数据:{}", studentDO);

        StudentDO currentThreadResult = singleStudentMapper.findByNumber(number);

        Assert.notNull(currentThreadResult, "当前线程查询结果为空");

        log.info("单库单表数据源事务内当前线程查询:{}", currentThreadResult);

        AtomicReference<StudentDO> otherThreadReference = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            StudentDO otherThreadResult = singleStudentMapper.findByNumber(number);
            latch.countDown();
            otherThreadReference.set(otherThreadResult);
        }).start();

        latch.await();
        Assert.isNull(otherThreadReference.get(), "并发线程查询结果不为空");

        log.info("单库单表数据源事务内并发线程查询为空");

        singleStudentMapper.delete(number);

        log.info("单库单表数据源事务内清理测试数据");

        log.info("------------- 单库单表数据源事务内操作演示 ---------------");

        return currentThreadResult;
    }

    public StudentDO singleDataSourceTest() throws Exception {
        org.springframework.util.Assert.notNull(singleStudentMapper, "请确保 MybatisConfiguration 类中的 @Configuration 注释已打开");

        log.info("------------- 单库单表数据源不开事务操作演示 ---------------");

        StudentDO studentDO = new StudentDO();
        studentDO.setNumber(number);
        studentDO.setName("Rose");
        studentDO.setScore("99");
        singleStudentMapper.insert(studentDO);

        log.info("单库单表数据源插入数据:{}", studentDO);

        StudentDO currentThreadResult = singleStudentMapper.findByNumber(number);

        Assert.notNull(currentThreadResult, "当前线程查询结果为空");

        log.info("单库单表数据源当前线程查询:{}", currentThreadResult);

        AtomicReference<StudentDO> otherThreadReference = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            StudentDO otherThreadResult = singleStudentMapper.findByNumber(number);
            latch.countDown();
            otherThreadReference.set(otherThreadResult);
        }).start();

        latch.await();

        Assert.notNull(otherThreadReference.get(), "并发线程查询结果为空");

        log.info("单库单表数据源并发线程查询:{}", currentThreadResult);

        singleStudentMapper.delete(number);

        log.info("单库单表数据源不开事务清理测试数据");

        log.info("------------- 单库单表数据源不开事务操作演示 ---------------");

        return currentThreadResult;
    }

    @Transactional(transactionManager = "txManagerForSharding")
    public StudentDO shardingDataSourceTransactionTest() throws Exception {

        log.info("------------- 分库分表数据源事务内操作演示 ---------------");

        Long id = Math.abs(Long.valueOf(new Random().nextInt()));

        shardingStudentMapper.deleteWithId(id, number);

        StudentDO studentDO = new StudentDO();
        studentDO.setId(id);
        studentDO.setNumber(number);
        studentDO.setName("Rose");
        studentDO.setScore("99");
        shardingStudentMapper.insert(studentDO);

        log.info("分库分表数据源事务内插入数据:{}", studentDO);

        StudentDO currentThreadResult = shardingStudentMapper.findByNumberWithId(id, number);

        Assert.notNull(currentThreadResult, "当前线程查询结果为空");

        log.info("分库分表数据源事务内当前线程查询:{}", currentThreadResult);

        AtomicReference<StudentDO> otherThreadReference = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            StudentDO otherThreadResult = shardingStudentMapper.findByNumberWithId(id, number);
            latch.countDown();
            otherThreadReference.set(otherThreadResult);
        }).start();

        latch.await();
        Assert.isNull(otherThreadReference.get(), "并发线程查询结果不为空");

        log.info("分库分表数据源事务内并发线程查询为空");

        shardingStudentMapper.deleteWithId(id, number);

        log.info("分库分表数据源事务内清理测试数据");

        log.info("------------- 分库分表数据源事务内操作演示 ---------------");

        return studentDO;
    }

    public StudentDO shardingDataSourceTest() throws Exception {
        org.springframework.util.Assert.notNull(shardingStudentMapper, "请确保 MybatisConfiguration 类中的 @Configuration 注释已打开");

        log.info("------------- 分库分表数据源不开事务操作演示 ---------------");

        Long id = Math.abs(Long.valueOf(new Random().nextInt()));

        shardingStudentMapper.deleteWithId(id, number);

        StudentDO studentDO = new StudentDO();
        studentDO.setId(id);
        studentDO.setNumber(number);
        studentDO.setName("Rose");
        studentDO.setScore("99");
        shardingStudentMapper.insert(studentDO);

        log.info("分库分表数据源插入数据:{}", studentDO);

        StudentDO currentThreadResult = shardingStudentMapper.findByNumberWithId(id, number);

        Assert.notNull(currentThreadResult, "当前线程查询结果为空");

        log.info("分库分表数据源当前线程查询:{}", currentThreadResult);

        AtomicReference<StudentDO> otherThreadReference = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            StudentDO otherThreadResult = shardingStudentMapper.findByNumberWithId(id, number);
            latch.countDown();
            otherThreadReference.set(otherThreadResult);
        }).start();

        latch.await();

        Assert.notNull(otherThreadReference.get(), "并发线程查询结果为空");

        log.info("分库分表数据源并发线程查询:{}", currentThreadResult);

        singleStudentMapper.delete(number);

        log.info("分库分表数据源不开事务清理测试数据");

        log.info("------------- 分库分表数据源不开事务操作演示 ---------------");

        return studentDO;
    }
}
