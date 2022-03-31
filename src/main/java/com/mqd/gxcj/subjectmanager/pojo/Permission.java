package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("p_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("菜单名字")
    private String name;

    @ApiModelProperty("菜单路径")
    private String path;

    @ApiModelProperty("父级菜单id")
    private Integer parent;

    @ApiModelProperty("菜单功能，只有最低级的菜单才有")
    private String permission;


}
