package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ApprovalProjectForm {
    @NotNull
    @ApiModelProperty("项目id")
    private String id;

    @ApiModelProperty("立项不通过")
    private String opinion;
}
