package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用于展示用户的基本信息")
public class UserVo {
    @ApiModelProperty("编号，用于登录")
    private String id;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户性别 0女 1男")
    private Boolean sex;

    @ApiModelProperty("用户头像url地址")
    private String avatar;

    @ApiModelProperty("最高学历，外键到学历表")
    private String education;

    @ApiModelProperty("毕业院校")
    private String graduateInstitution;

    @ApiModelProperty("毕业专业")
    private String specialization;

    @ApiModelProperty("毕业时间")
    private LocalDate graduateDate;

    @ApiModelProperty("职务，外键到职务表")
    private String duty;

    @ApiModelProperty("职称，外键到职称表")
    private String rank;

    @ApiModelProperty("出生年月日")
    private LocalDate birth;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("籍贯")
    private String origo;

    @ApiModelProperty("现住址")
    private String address;

    @ApiModelProperty("家庭电话")
    private String homeTelephone;

    @ApiModelProperty("办公电话")
    private String officeTelephone;

    @ApiModelProperty("移动电话")
    private String mobile1;

    @ApiModelProperty("备用电话")
    private String mobile2;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("入职日期")
    private LocalDate entryTime;

    @ApiModelProperty("所属学科id，外键到学科表")
    private String subject;

    @ApiModelProperty("备注")
    private String description;
}
