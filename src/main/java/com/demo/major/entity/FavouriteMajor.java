package com.demo.major.entity;

import com.demo.school.entity.School;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@SuppressWarnings("unused")
@Data
@Table(name = "t_favourite_major")
@ApiModel(value = "兴趣专业表")
public class FavouriteMajor {
    @Id
    public Long id;
    @ApiModelProperty(value = "学校id")
    public Long schoolId;
    @ApiModelProperty(value = "学校名")
    public String schoolName;
    @ApiModelProperty(value = "学校id")
    public Long majorId;
    @ApiModelProperty(value = "专业名")
    public String majorName;
    @ApiModelProperty(value = "学生id")
    public Long userId;
    @ApiModelProperty(value = "学生姓名")
    public String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    @Transient
    public School school;
}

