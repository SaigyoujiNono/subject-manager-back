package com.mqd.gxcj.subjectmanager.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenditureAndProjectVo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("申请人id")
    private String applicationUserId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("用途")
    private String purpose;

    @ApiModelProperty("申请经费")
    private BigDecimal expenditure;

    @ApiModelProperty("审核状态，committed已提交，checked审核通过，no-checked审核为通过")
    private String checkStatus;

    @ApiModelProperty("审核人id")
    private String checker;

    @ApiModelProperty("审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty("提交时间")
    private LocalDateTime committedTime;

    @ApiModelProperty("未通过审核的意见")
    private String noCheckedOpinion;

    @ApiModelProperty("项目信息")
    private Project project;

    @ApiModelProperty("申请人信息")
    private User user;
}
