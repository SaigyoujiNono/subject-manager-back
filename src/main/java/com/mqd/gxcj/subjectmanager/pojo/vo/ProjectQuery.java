package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "项目查询表单", description = "")
public class ProjectQuery {
    @ApiModelProperty("项目名称")
    @Size(max = 255)
    private String name;

    @ApiModelProperty("所属学科")
    private List<Integer> subjectIds;

    @ApiModelProperty("创建时间-开始")
    private LocalDate startTime;

    @ApiModelProperty("创建时间-结束")
    private LocalDate endTime;
}
