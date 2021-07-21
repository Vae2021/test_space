package com.alipay.demo.dal.zdal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Zdal + JDBC + Transaction 使用示例
 */
@Component
public class ZdalJdbcSample {
    private static final Logger log = LoggerFactory.getLogger(ZdalJdbcSample.class);

    @Autowired(required = false)
    @Qualifier("singleDataSource")
    private DataSource dataSource;

    public String doJdbc() throws SQLException {
        org.springframework.util.Assert.notNull(dataSource, "请确保 zdal.xml 文件中的注释已去掉");
        String insertID = "";
        try (Connection connectionA = dataSource.getConnection(); Connection connectionB = dataSource.getConnection()){
            log.info("链接A开启事务");
            connectionA.setAutoCommit(false);
            String number = UUID.randomUUID().toString();
            String insertSql = "INSERT INTO student (number, name, score) values (?, ?, ?)";
            PreparedStatement insertPreparedStatement = connectionA.prepareStatement(insertSql);
            insertPreparedStatement.setString(1, number);
            insertPreparedStatement.setString(2, "Jack");
            insertPreparedStatement.setString(3, "100");
            insertPreparedStatement.execute();
            log.info("链接A插入数据");

            String selectSql = "select id, number, name, score from student where number = ?";
            PreparedStatement queryPreparedStatement = connectionB.prepareStatement(selectSql);
            queryPreparedStatement.setString(1, number);
            ResultSet resultSet = queryPreparedStatement.executeQuery();

            Assert.isTrue(!resultSet.next(), "查询结果不为空");
            log.info("链接B查询数据, 由于链接A的事务未提交，查询结果为空");

            connectionA.commit();
            log.info("链接A提交事务");

            resultSet = queryPreparedStatement.executeQuery();
            while (resultSet.next()) {
                insertID = String.valueOf(resultSet.getLong(1));
                Assert.isTrue(number.equals(resultSet.getString(2)), "查询结果有误");
                Assert.isTrue("Jack".equals(resultSet.getString(3)), "查询结果有误");
                Assert.isTrue("100".equals(resultSet.getString(4)), "查询结果有误");
            }
            log.info("链接B再次查询数据, 由于链接A的事务已提交，查询结果非空");

            String deleteSql = "delete from student where number = ?";
            PreparedStatement deletePreparedStatement = connectionB.prepareStatement(deleteSql);
            deletePreparedStatement.setString(1, number);
            deletePreparedStatement.executeUpdate();
           log.info("链接B删除数据");
        }
        return insertID;
    }
}
