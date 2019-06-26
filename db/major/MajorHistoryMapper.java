package com.demo.db.major;

import com.demo.major.entity.Major;
import com.demo.major.entity.MajorHistory;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MajorHistoryMapper extends TkMapper<MajorHistory> {

    @Select("select * from t_major where `username` = #{username} and `password` = #{password} ")
    Major selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_major where `name` = #{name} and `school_id` = #{schoolId}  and `subject_category` = #{subjectCategory}  and `degree_category` = #{degreeCategory} ")
    Major selectByName(@Param("name") String username, @Param("schoolId") Long schoolId,
                       @Param("subjectCategory") String subjectCategory, @Param("degreeCategory") String degreeCategory);

    @Select("select * from t_major_history where `school_id` = #{schoolId}")
    List<MajorHistory> selectBySchoolId(@Param("schoolId") Long schoolId);

    @Select("select * from t_major_history where `area` = #{area} and `major_name` = #{majorName} and `lowest` <= #{score} and `year` = #{year} order by `lowest` desc")
    List<MajorHistory> selectByKey(@Param("area") String area, @Param("majorName") String majorName,  @Param("year") Integer year,@Param("score") Integer score);

    List<MajorHistory> selectByKeyword(@Param("subjectCategory") String subjectCategory, @Param("year") Integer year, @Param("keyword") String keyword);

    @Select("select * from t_major_history where `school_id` = #{schoolId} and `major_id` = #{majorId} order by `year` asc")
    List<MajorHistory> selectBySchoolIdMajorId(@Param("schoolId") Long schoolId, @Param("majorId") Long majorId);
}
