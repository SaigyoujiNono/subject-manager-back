package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "项目材料审核提交表单", description = "")
public class CheckProjectForm {
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("专家列表")
    private List<String> expertList;

    @ApiModelProperty("审核不通过意见")
    private String opinion;
}
