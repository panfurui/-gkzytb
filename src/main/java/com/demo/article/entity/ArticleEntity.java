package com.demo.article.entity;

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
@Table(name = "t_article")
@ApiModel(value = "文章表")
public class ArticleEntity {
    @Id
    public Long id;
    @ApiModelProperty(value = "标题")
    public String title;
    @ApiModelProperty(value = "图片")
    public String img;
    @ApiModelProperty(value = "内容")
    public String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    public Date createTime;
    @Transient
    public MultipartFile file;
}

