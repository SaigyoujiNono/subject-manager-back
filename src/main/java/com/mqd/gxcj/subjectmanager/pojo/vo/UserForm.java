package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class UserForm {

    @ApiModelProperty("编号，用于登录")
    @Size(min = 5,max = 20)
    @NotBlank
    private String id;

    @ApiModelProperty("用户姓名")
    @Size(min = 2,max = 20)
    @NotBlank
    private String name;

    @ApiModelProperty("用户性别 0女 1男")
    @NotNull
    private Boolean sex;

    @ApiModelProperty("用户头像url地址")
    @Size(max = 255)
    private String avatar;

    @ApiModelProperty("最高学历，外键到学历表")
    @NotNull
    private Integer education;

    @ApiModelProperty("毕业院校")
    @NotBlank
    @Size(max = 40)
    private String graduateInstitution;

    @ApiModelProperty("毕业专业")
    @NotBlank
    @Size(max = 40)
    private String specialization;

    @ApiModelProperty("毕业时间")
    @NotNull
    private LocalDate graduateDate;

    @ApiModelProperty("职务，外键到职务表")
    @NotNull
    private Integer duty;

    @ApiModelProperty("职称，外键到职称表")
    @NotNull
    private Integer rank;

    @ApiModelProperty("出生年月日")
    @NotNull
    private LocalDate birth;

    @ApiModelProperty("国家")
    @Size(max = 50)
    private String country;

    @ApiModelProperty("籍贯")
    @Size(max = 255)
    private String origo;

    @ApiModelProperty("现住址")
    @Size(max = 255)
    private String address;

    @ApiModelProperty("家庭电话")
    @Size(max = 18)
    private String homeTelephone;

    @ApiModelProperty("办公电话")
    @Size(max = 18)
    private String officeTelephone;

    @ApiModelProperty("移动电话")
    @NotBlank
    @Size(max = 18)
    private String mobile1;

    @ApiModelProperty("备用电话")
    @Size(max = 18)
    private String mobile2;

    @ApiModelProperty("电子邮箱")
    @Size(max = 40)
    @NotBlank
    private String email;

    @ApiModelProperty("入职日期")
    @NotNull
    private LocalDate entryTime;

    @ApiModelProperty("所属学科id，外键到学科表")
    @NotNull
    private Integer subjectId;

    @ApiModelProperty("备注")
    @Size(max = 255)
    private String description;

    @ApiModelProperty("角色")
    @NotNull
    private Integer role;
}
