package com.demo.area.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Data
@Table(name = "t_line_history")
@ApiModel(value = "区域分数线表")
public class LineHistory {
    @Id
    public Long id;
    @ApiModelProperty(value = "地区，各个省")
    public String area;
    @ApiModelProperty(value = "年份")
    public Integer year;
    @ApiModelProperty(value = "理科第一批分数线")
    public Integer line1;
    @ApiModelProperty(value = "理科第二批分数线")
    public Integer line2;
    @ApiModelProperty(value = "理科第三批分数线")
    public Integer line3;
    @ApiModelProperty(value = "文科第一批分数线")
    public Integer line4;
    @ApiModelProperty(value = "文科第二批分数线")
    public Integer line5;
    @ApiModelProperty(value = "文科第三批分数线")
    public Integer line6;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;
}

