package com.mqd.gxcj.subjectmanager.pojo.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel(value = "项目经费申请单修改")
public class UpdateProjectExpenditureForm {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("名称")
    @NotBlank
    private String name;

    @ApiModelProperty("用途")
    @NotBlank
    private String purpose;

    @ApiModelProperty("申请经费")
    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal expenditure;
}
