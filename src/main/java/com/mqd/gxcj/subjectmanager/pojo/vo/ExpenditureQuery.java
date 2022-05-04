package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ExpenditureQuery {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("最小申请经费")
    private BigDecimal minExpenditure;

    @ApiModelProperty("最大申请经费")
    private BigDecimal maxExpenditure;

    @ApiModelProperty("开始提交时间")
    private LocalDateTime minCommittedTime;

    @ApiModelProperty("结束提交时间")
    private LocalDateTime maxCommittedTime;

    @ApiModelProperty("审核状态，committed已提交，checked审核通过，no-checked审核为通过")
    private String checkStatus;
}
