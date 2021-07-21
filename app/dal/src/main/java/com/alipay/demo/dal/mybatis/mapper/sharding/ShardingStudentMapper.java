package com.alipay.demo.dal.mybatis.mapper.sharding;

import com.alipay.demo.dal.mybatis.StudentDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用于关联 SQL 语句的 Mapper
 */
@Mapper
public interface ShardingStudentMapper {

    @Insert("insert into sharding_student(id, number, name, score) values (#{id}, #{number}, #{name}, #{score})")
    void insert(StudentDO student);

    @Select("select id, number, name, score from sharding_student where id = #{id} and number = #{number}")
    StudentDO findByNumberWithId(@Param("id") Long id , @Param("number") String number);

    @Delete("delete from sharding_student where id = #{id} and number = #{number}")
    void deleteWithId(@Param("id") Long id , @Param("number") String number);
}
