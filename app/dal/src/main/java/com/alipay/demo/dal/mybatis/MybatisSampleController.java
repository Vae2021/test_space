package com.alipay.demo.dal.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 本接口用于演示Mybatis的使用，本地运行前请启动 ODP
 * 注意：本接口使用的是主站 sofaappcenter 作为示例应用
 * 数据库 DMS 地址： zdaldemo@obproxy.ocp2.alipay.net:2883:infra_public_dev0_2769#def【infra_public_dev0_2769】
 * 管控台 DDS 地址： http://ddsconsole.stable.alipay.net/index.html#/ALIPAY/dev/zdal/appds/sofaappcenter/sofabootdemo/RDB?_k=goi2ll
 * <p> 1、本地安装 ODP 简化流程如下，教程详见: https://yuque.antfin-inc.com/mesh/service-mesh/native-mesh-boot
 *     * 1.1 前提依赖，本地需要装 docker
 *     * 1.2 安装 meshboot 命令 bash <(curl -sL http://native-mesh.alipay.net/meshboot/install.sh)
 *     * 1.3 meshboot start -m odp -a sofaappcenter
 * <p> 2、注意! 示例数据源的声明在 dal 模块下 src/main/resources/spring/dal.xml 文件中, 需要取消该文件中的注释才能执行本用例
 * <p> 3、Mapper 的创建在 com.alipay.demo.dal.mybatis.MybatisConfiguration 类中，需要取消该类上的 @Configuration 注解的注释
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisSampleController {

    @Autowired
    private MybatisSample mybatisSample;

    @GetMapping("/single")
    public List<StudentDO> testSingle() throws Exception {
        List<StudentDO> studentDOList = new ArrayList<>();
        studentDOList.add(mybatisSample.singleDataSourceTest());
        studentDOList.add(mybatisSample.singleDataSourceTransactionTest());
        return studentDOList;
    }

    @GetMapping("/sharding")
    public List<StudentDO> testSharding() throws Exception {
        List<StudentDO> studentDOList = new ArrayList<>();
        studentDOList.add(mybatisSample.shardingDataSourceTest());
        studentDOList.add(mybatisSample.shardingDataSourceTransactionTest());
        return studentDOList;
    }
}
