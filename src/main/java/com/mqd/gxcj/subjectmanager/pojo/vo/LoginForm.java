package com.mqd.gxcj.subjectmanager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "登录表单", description = "")
public class LoginForm {

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("是否记住")
    private Boolean rememberMe;
}
