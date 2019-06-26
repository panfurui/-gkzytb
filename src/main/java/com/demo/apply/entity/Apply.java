package com.demo.apply.entity;

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
@Table(name = "t_apply")
@ApiModel(value = "申请表")
public class Apply {
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
    @ApiModelProperty(value = "志愿排名")
    public Integer number;
    @Transient
    public Integer order;
    @ApiModelProperty(value = "是否调剂")
    public Integer isAdjust;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    @Transient
    @ApiModelProperty(value = "地区，各个省")
    public String area;
    @Transient
    @ApiModelProperty(value = "详细地址")
    public String address;
    @Transient
    @ApiModelProperty(value = "咨询方式")
    public String phone;
    @Transient
    @ApiModelProperty(value = "学校描述")
    public String description;
    @Transient
    @ApiModelProperty(value = "是否211")
    public Integer is211;
    @Transient
    @ApiModelProperty(value = "是否985")
    public Integer is985;
    @Transient
    @ApiModelProperty(value = "本科学科点")
    public Integer bachelorCount;
    @Transient
    @ApiModelProperty(value = "硕士学科点")
    public Integer masterCount;
    @Transient
    @ApiModelProperty(value = "博士学科点")
    public Integer doctorCount;
}

