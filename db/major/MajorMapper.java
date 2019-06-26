package com.demo.db.major;

import com.demo.major.entity.Major;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MajorMapper extends TkMapper<Major> {

    @Select("select * from t_major where `username` = #{username} and `password` = #{password} ")
    Major selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_major where `name` = #{name} and `school_id` = #{schoolId}  and `subject_category` = #{subjectCategory}  and `degree_category` = #{degreeCategory} ")
    Major selectByName(@Param("name") String username, @Param("schoolId") Long schoolId,
                       @Param("subjectCategory") String subjectCategory, @Param("degreeCategory") String degreeCategory);

    @Select("select * from t_major where `school_id` = #{schoolId}")
    List<Major> selectAllBySchoolId(@Param("schoolId") Long schoolId);

    List<Major> selectByKey(@Param("keyword") String keyword);
}
