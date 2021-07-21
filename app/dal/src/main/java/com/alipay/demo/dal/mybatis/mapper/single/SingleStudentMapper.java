package com.alipay.demo.dal.mybatis.mapper.single;

import com.alipay.demo.dal.mybatis.StudentDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用于关联 SQL 语句的 Mapper
 */
@Mapper
public interface SingleStudentMapper {

    @Insert("insert into student(number, name, score) values (#{number}, #{name}, #{score})")
    void insert(StudentDO student);

    @Select("select id, number, name, score from student where number = #{number}")
    StudentDO findByNumber(String number);

    @Delete("delete from student where number = #{number}")
    void delete(String number);
}
