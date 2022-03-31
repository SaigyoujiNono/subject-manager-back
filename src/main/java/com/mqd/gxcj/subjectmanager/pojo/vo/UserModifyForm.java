package com.mqd.gxcj.subjectmanager.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "UserModify对象", description = "用户修改个人信息所需要的中间表")
public class UserModifyForm {

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("提交修改的用户id")
    private String userId;

    @ApiModelProperty("需要修改的原因")
    private String reason;

    @ApiModelProperty("修改后的名字")
    private String name;

    @ApiModelProperty("修改后的学历信息")
    private Integer education;

    @ApiModelProperty("修改后的毕业学校")
    private String graduateInstitution;

    @ApiModelProperty("修改后的毕业专业")
    private String specialization;

    @ApiModelProperty("修改后的毕业时间")
    private LocalDateTime graduateDate;

    @ApiModelProperty("修改后的职务")
    private Integer duty;

    @ApiModelProperty("修改后的职称")
    private Integer rank;

    @ApiModelProperty("修改后的生日")
    private LocalDateTime birth;

    @ApiModelProperty("修改后的国家")
    private String country;

    @ApiModelProperty("修改后的籍贯")
    private String origo;

    @ApiModelProperty("修改后的住址")
    private String address;

    @ApiModelProperty("修改后的家庭电话")
    private String homeTelephone;

    @ApiModelProperty("修改后的办公电话")
    private String officeTelephone;

    @ApiModelProperty("修改后的移动电话")
    private String mobile1;

    @ApiModelProperty("修改后的备用电话")
    private String mobile2;

    @ApiModelProperty("修改后的学科id")
    private Integer subjectId;
}
