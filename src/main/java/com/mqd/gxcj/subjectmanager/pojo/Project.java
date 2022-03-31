package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目申报表，专家id会有1个以上，单独存储在project_expert表
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_project")
@ApiModel(value = "Project对象", description = "项目申报表，专家id会有1个以上，单独存储在project_expert表")
public class Project implements Serializable {
    //用户提交项目申请之后的状态
    public static final String UNCHECKED = "uncheck";
    //用户未通过材料审核的状态
    public static final String NO_CHECKED = "no-checked";
    //通过材料审核之后进入专家评审
    public static final String EXPERT = "expert";
    //项目立项阶段
    public static final String COMMITTED = "committed";
    //验收阶段
    public static final String COMPLETING = "completing";



    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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

    @ApiModelProperty("最终验收日期")
    private LocalDate finalReviewTime;

    @ApiModelProperty("最终消耗经费")
    private BigDecimal finalExpenditure;

    @ApiModelProperty("最终验收结果，uncheck尚未审核，committed申报阶段，expert专家评审阶段，checked项目立项，completed验收完成")
    private String status;


}
