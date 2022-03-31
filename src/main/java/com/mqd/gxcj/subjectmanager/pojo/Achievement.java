package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_achievement")
@ApiModel(value = "Achievement对象", description = "")
public class Achievement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("论文名称")
    private String title;

    @ApiModelProperty("第一作者")
    private String firstAuthor;

    @ApiModelProperty("第二作者")
    private String secondAuthor;

    @ApiModelProperty("第三作者")
    private String third;

    @ApiModelProperty("发表日期")
    private LocalDate publishDate;

    @ApiModelProperty("发表刊物")
    private String publication;

    @ApiModelProperty("论文类型")
    private String type;

    @ApiModelProperty("论文等级")
    private String grade;

    @ApiModelProperty("状态，checking待检查，checked通过，no-checked不通过")
    private String checkStatus;

    @ApiModelProperty("检查时间")
    private LocalDate checkDate;

    @ApiModelProperty("检查人")
    private String checker;

    @ApiModelProperty("检查不通过意见")
    private String checkerFailureOpinion;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
