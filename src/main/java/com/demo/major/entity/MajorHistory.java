package com.demo.major.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Data
@Table(name = "t_major_history")
@ApiModel(value = "专业历史表")
public class MajorHistory {
    @Id
    public Long id;
    @ApiModelProperty(value = "学校id")
    public Long schoolId;
    @ApiModelProperty(value = "学校名")
    public String schoolName;
    @ApiModelProperty(value = "地区")
    public String area;
    @ApiModelProperty(value = "学校id")
    public Long majorId;
    @ApiModelProperty(value = "专业名")
    public String majorName;
    @ApiModelProperty(value = "科目类别")
    public String subjectCategory;
    @ApiModelProperty(value = "批次")
    public String lineType;
    @ApiModelProperty(value = "年份")
    public Integer year;
    @ApiModelProperty(value = "录取人数")
    public Integer count;
    @ApiModelProperty(value = "最高分")
    public Integer highest;
    @ApiModelProperty(value = "最低分")
    public Integer lowest;
    @ApiModelProperty(value = "平均分")
    public Integer average;
    @ApiModelProperty(value = "就业率")
    public Integer rate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;
}

