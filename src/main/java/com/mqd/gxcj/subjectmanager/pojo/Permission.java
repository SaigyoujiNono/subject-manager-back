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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * <p>
 * 
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("p_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @Null
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限名称")
    @NotBlank
    private String permission;

    @NotBlank
    @ApiModelProperty("权限描述")
    private String description;


}
