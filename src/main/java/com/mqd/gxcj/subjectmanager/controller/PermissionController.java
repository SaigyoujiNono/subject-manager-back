package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Permission;
import com.mqd.gxcj.subjectmanager.pojo.vo.RolePermForm;
import com.mqd.gxcj.subjectmanager.service.PermissionService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
@Api(tags = "权限控制接口")
@RestController
@RequestMapping("/permission")
@SaCheckLogin
@SaCheckRole(value = "admin")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "添加权限")
    @PostMapping("/add")
    public R addPermission(@RequestBody @Validated Permission permission) throws AppException {
        boolean save = permissionService.save(permission);
        if (!save) {
            throw new AppException(RStatus.ERROR);
        }
        return R.ok();
    }

    @ApiOperation(value = "返回所有的权限")
    @GetMapping("/get")
    public R getAllPermission() {
        return R.ok().put("permissions", permissionService.list());
    }

    @ApiOperation(value = "根据角色id返回权限")
    @GetMapping("/getByRole")
    public R getPermissionByRole(Integer roleId) {
        List<Permission> perms = permissionService.getPermissionByRoleId(roleId);
        return R.ok().put("perms", perms);
    }

    @ApiOperation(value = "根据角色id保存权限配置")
    @PostMapping("/savePerms")
    public R savePermsByRole(@RequestBody @Validated RolePermForm rolePermForm) throws AppException {
        boolean save = permissionService.saveRolePerms(rolePermForm);
        if (!save) {
            throw new AppException(RStatus.ERROR);
        }
        return R.ok();
    }
}
