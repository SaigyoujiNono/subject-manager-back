package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 经费申请表
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_project_expenditure")
@ApiModel(value = "ProjectExpenditure对象", description = "经费申请表")
public class ProjectExpenditure implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty("审核状态，commited已提交，checked审核通过，no-checked审核为通过")
    private String checkStatus;

    @ApiModelProperty("审核人id")
    private String checker;

    @ApiModelProperty("审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty("提交时间")
    private LocalDateTime commitedTime;


}
