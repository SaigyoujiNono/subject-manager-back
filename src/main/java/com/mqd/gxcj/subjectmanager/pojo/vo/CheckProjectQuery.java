package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@ApiModel(value = "项目待审核列表", description = "")
public class CheckProjectQuery {

    @ApiModelProperty("项目名称")
    @Size(max = 255)
    private String name;

    @ApiModelProperty("所属学科")
    private Integer subjectId;
}
