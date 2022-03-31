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
@TableName("s_account")
@ApiModel(value = "Account对象", description = "")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户主键")
    private String id;

    @ApiModelProperty("用户密码")
    private String password;


}
