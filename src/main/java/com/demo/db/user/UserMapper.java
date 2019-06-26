package com.demo.db.user;

import com.demo.user.entity.UserEntity;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends TkMapper<UserEntity> {

    @Select("select * from t_user where `username` = #{username} and `password` = #{password} ")
    UserEntity selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_user where `username` = #{username}")
    UserEntity selectByUsername(@Param("username") String username);

    @Select("select * from t_user where `username` <> 'admin'")
    List<UserEntity> selectAllNotAdmin();
}
