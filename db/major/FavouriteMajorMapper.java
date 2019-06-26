package com.demo.db.major;

import com.demo.major.entity.FavouriteMajor;
import com.demo.major.entity.Major;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FavouriteMajorMapper extends TkMapper<FavouriteMajor> {

    @Select("select * from t_major where `username` = #{username} and `password` = #{password} ")
    Major selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_favourite_major where `school_id` = #{schoolId}  and `major_id` = #{majorId}  and `user_id` = #{userId} ")
    FavouriteMajor selectByKey(@Param("schoolId") Long schoolId, @Param("majorId") Long username,
                      @Param("userId") Long userId);

    @Select("select * from t_favourite_major where `user_id` = #{userId} ")
    List<FavouriteMajor> selectByUserId(@Param("userId") Long userId);

    @Select("select * from t_major where `username` <> 'admin'")
    List<Major> selectAllNotAdmin();
}
