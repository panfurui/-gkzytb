package com.demo.db.area;

import com.demo.area.entity.LineHistory;
import com.demo.school.entity.School;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LineHistoryMapper extends TkMapper<LineHistory> {

    @Select("select * from t_line_history where `area` = #{area} and `year` = #{year}")
    LineHistory selectByKey(@Param("area") String area, @Param("year") Integer year);
}
