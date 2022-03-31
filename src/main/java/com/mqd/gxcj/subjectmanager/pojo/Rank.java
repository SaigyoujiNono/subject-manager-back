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
 * 大学教师职称表
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_rank")
@ApiModel(value = "Rank对象", description = "大学教师职称表")
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("职称名称")
    private String name;

    @ApiModelProperty("备注")
    private String description;


}
