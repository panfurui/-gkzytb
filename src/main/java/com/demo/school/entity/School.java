package com.demo.school.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@SuppressWarnings("unused")
@Data
@Table(name = "t_school")
@ApiModel(value = "学校信息表")
public class School {
    @Id
    public Long id;
    @ApiModelProperty(value = "学校名")
    public String name;
    @ApiModelProperty(value = "地区，各个省")
    public String area;
    @ApiModelProperty(value = "详细地址")
    public String address;
    @ApiModelProperty(value = "咨询方式")
    public String phone;
    @ApiModelProperty(value = "学校描述")
    public String description;
    @ApiModelProperty(value = "是否211")
    public Integer is211;
    @ApiModelProperty(value = "是否985")
    public Integer is985;
    @ApiModelProperty(value = "本科学科点")
    public Integer bachelorCount;
    @ApiModelProperty(value = "硕士学科点")
    public Integer masterCount;
    @ApiModelProperty(value = "博士学科点")
    public Integer doctorCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;
    @ApiModelProperty(value = "图片")
    public String img;
    @Transient
    public MultipartFile file;
}

