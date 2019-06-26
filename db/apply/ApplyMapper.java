package com.demo.db.apply;

import com.demo.apply.entity.Apply;
import com.demo.school.entity.School;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApplyMapper extends TkMapper<Apply> {

    @Select("select * from t_apply where `school_id` = #{schoolId}  and `major_id` = #{majorId}  and `user_id` = #{userId} ")
    Apply selectByKey(@Param("schoolId") Long schoolId, @Param("majorId") Long username,
                      @Param("userId") Long userId);

    @Select("select count(*) from t_apply where `user_id` = #{userId}")
    int countByUserId(@Param("userId") Long userId);


    @Select("select * from t_apply where `user_id` = #{userId}")
    List<Apply> selectByUserId(@Param("userId") Long userId);
}
