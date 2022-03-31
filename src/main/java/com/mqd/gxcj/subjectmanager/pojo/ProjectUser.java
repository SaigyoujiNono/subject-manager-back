package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参与项目的学科成员
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_project_user")
@ApiModel(value = "ProjectUser对象", description = "参与项目的学科成员")
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String projectId;

    private String userId;


}
