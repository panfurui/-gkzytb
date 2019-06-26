package com.demo.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Data
@Table(name = "t_user")
@ApiModel(value = "用户信息表")
public class UserEntity {
    @Id
    public Long id;
    @ApiModelProperty(value = "用户名")
    public String username;
    @ApiModelProperty(value = "密码")
    public String password;
    @ApiModelProperty(value = "姓名")
    public String name;
    @ApiModelProperty(value = "地址")
    public String address;
    @ApiModelProperty(value = "联系方式")
    public String phone;
    @ApiModelProperty(value = "角色，1-超级管理员，2-平台管理员，3-爱心人士，4-学生负责人")
    public Integer role;
}

