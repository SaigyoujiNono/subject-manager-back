package com.mqd.gxcj.subjectmanager.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "项目申报表单", description = "项目申报表，专家id会有1个以上，单独存储在project_expert表")
public class AppProjectForm {

    @ApiModelProperty("负责人id，外键到用户表")
    @NotBlank
    @Size(max = 20)
    private String principal;

    @ApiModelProperty("项目名称")
    @NotBlank
    @Size(max = 255)
    private String name;

    @ApiModelProperty("所属学科")
    @NotNull
    private Integer subjectId;

    @ApiModelProperty("项目方向")
    @NotBlank
    @Size(max = 255)
    private String direction;

    @ApiModelProperty("预期经费")
    @NotNull
    private BigDecimal expenditure;

    @ApiModelProperty("项目说明")
    @NotBlank
    private String explanation;

    @ApiModelProperty("最终验收日期")
    @NotNull
    private LocalDate finalReviewTime;

    @ApiModelProperty("参与人员")
    private List<String> member;

    @ApiModelProperty("项目材料url")
    private String material;
}
