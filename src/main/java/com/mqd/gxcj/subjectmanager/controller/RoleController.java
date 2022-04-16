package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Role;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色表，主要用户存储用户的身份信息 前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "角色控制接口")
@RestController
@RequestMapping("/role")
@SaCheckLogin
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "获取所有的角色信息")
    @GetMapping("/all")
    public R getAllRole() throws AppException {
        List<Role> list = roleService.list();
        if (list == null) {
            throw new AppException(RStatus.ERROR);
        }
        return R.ok().put("roles", list);
    }
}
