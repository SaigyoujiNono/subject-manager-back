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
 * 
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("p_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单英文名")
    private String name;

    @ApiModelProperty("菜单路径")
    private String path;

    @ApiModelProperty("父级菜单id")
    private Integer parent;

    @ApiModelProperty("菜单功能，只有最低级的菜单才有")
    private String permission;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("是否隐藏")
    private Boolean hidden;

    @ApiModelProperty("菜单名字")
    private String title;

    @ApiModelProperty("菜单icon")
    private String icon;


}
