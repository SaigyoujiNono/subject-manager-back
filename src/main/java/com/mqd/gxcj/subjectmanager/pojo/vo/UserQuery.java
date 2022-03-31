package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery {
    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户性别 0女 1男")
    private Boolean sex;

    @ApiModelProperty("最高学历，外键到学历表")
    private Integer education;

    @ApiModelProperty("职务，外键到职务表")
    private Integer duty;

    @ApiModelProperty("职称，外键到职称表")
    private Integer rank;

    @ApiModelProperty("所属学科id，外键到学科表")
    private Integer subjectId;
}
