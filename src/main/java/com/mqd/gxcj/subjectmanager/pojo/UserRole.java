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
 * 用户-角色表，用户与角色的关系是n-1
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("p_user_role")
@ApiModel(value = "UserRole对象", description = "用户-角色表，用户与角色的关系是n-1")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("角色id")
    private Integer roleId;


}
