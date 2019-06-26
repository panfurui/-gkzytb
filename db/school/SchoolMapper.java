package com.demo.db.school;

import com.demo.apply.entity.Apply;
import com.demo.school.entity.School;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchoolMapper extends TkMapper<School> {

    @Select("select * from t_school where `username` = #{username} and `password` = #{password} ")
    School selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_school where `name` = #{name}")
    School selectByName(@Param("name") String username);

    @Select("select * from t_school where `username` <> 'admin'")
    List<School> selectAllNotAdmin();

}
