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
@TableName("p_role_permission")
@ApiModel(value = "RolePermission对象", description = "")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("权限id")
    private Integer permissionId;


}
