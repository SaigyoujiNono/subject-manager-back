package com.mqd.gxcj.subjectmanager.pojo.vo;

import com.mqd.gxcj.subjectmanager.pojo.User;
import com.mqd.gxcj.subjectmanager.pojo.dto.ExpertOpinion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "项目的详细信息", description = "")
public class ProjectDetail {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("负责人id，外键到用户表")
    private String principal;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("所属学科")
    private Integer subjectId;

    @ApiModelProperty("项目方向")
    private String direction;

    @ApiModelProperty("预期经费")
    private BigDecimal expenditure;

    @ApiModelProperty("项目说明")
    private String explanation;

    @ApiModelProperty("附加材料下载地址")
    private String material;

    @ApiModelProperty("项目申报创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty("项目提交材料审核阶段，committed等待审核，checked审核通过，no-checked审核为通过")
    private String checked;

    @ApiModelProperty("审核不通过意见")
    private String noPassOpinion;

    @ApiModelProperty("审核日期")
    private LocalDateTime reviewTime;

    @ApiModelProperty("审核人id")
    private String reviewUId;

    @ApiModelProperty("审核人信息")
    private User reviewUser;

    @ApiModelProperty("最终验收日期")
    private LocalDate finalReviewTime;

    @ApiModelProperty("最终消耗经费")
    private BigDecimal finalExpenditure;

    @ApiModelProperty("最终验收结果，uncheck尚未审核，committed申报阶段，expert专家评审阶段，checked项目立项，completed验收完成")
    private String status;

    @ApiModelProperty("项目负责人信息")
    private User userDetail;

    @ApiModelProperty("项目负责人信息")
    private List<User> memberList;

    @ApiModelProperty("专家意见列表")
    private List<ExpertOpinion> expertizeList;
}
