package com.mqd.gxcj.subjectmanager.pojo.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mqd.gxcj.subjectmanager.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ApiModel("专家意见表")
@Data
@Accessors(chain = true)
public class ExpertOpinion {
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("专家id")
    private String userId;

    @ApiModelProperty("专家信息")
    private User detail;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("项目阶段，committed为初审阶段，finish为项目验收阶段")
    private String stage;

    @ApiModelProperty("专家意见")
    private String expertOpinion;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
