package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Accessors(chain = true)
@ApiModel(value = "经费审查表单", description = "用于接受审查经费的表单")
public class CheckExpenditureForm {

    @ApiModelProperty("经费单id")
    @NotNull
    private Integer id;

    @ApiModelProperty("未通过审核的意见")
    @Size(max = 255)
    private String opinion;
}
