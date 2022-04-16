package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_project_expertize")
@ApiModel(value = "ProjectExpertize对象", description = "专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表")
public class ProjectExpertize implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("专家id")
    private String userId;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("项目阶段，commited为初审阶段，finish为项目验收阶段")
    private String stage;

    @ApiModelProperty("专家意见")
    private String expertOpinion;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
