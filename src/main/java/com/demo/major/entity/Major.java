package com.demo.major.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Data
@Table(name = "t_major")
@ApiModel(value = "专业表")
public class Major {
    @Id
    public Long id;
    @ApiModelProperty(value = "学校id")
    public Long schoolId;
    @ApiModelProperty(value = "专业名")
    public String name;
    @ApiModelProperty(value = "科目类别")
    public String subjectCategory;
    @ApiModelProperty(value = "学位类别")
    public String degreeCategory;
    @ApiModelProperty(value = "所属批次")
    public String lineType;
    @ApiModelProperty(value = "专业描述")
    public String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    @Transient
    public MajorHistory history2018;
    @Transient
    public MajorHistory history2017;
    @Transient
    public MajorHistory history2016;
    @Transient
    public String schoolName;
}

