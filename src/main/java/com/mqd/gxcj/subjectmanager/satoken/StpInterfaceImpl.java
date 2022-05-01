package com.mqd.gxcj.subjectmanager.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.pojo.Permission;
import com.mqd.gxcj.subjectmanager.pojo.Role;
import com.mqd.gxcj.subjectmanager.pojo.RolePermission;
import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.mqd.gxcj.subjectmanager.service.PermissionService;
import com.mqd.gxcj.subjectmanager.service.RolePermissionService;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.mqd.gxcj.subjectmanager.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        List<Integer> roleIds = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", loginId)).stream()
                .map(UserRole::getRoleId).collect(Collectors.toList());
        if (roleIds.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> permIds = rolePermissionService.list(new QueryWrapper<RolePermission>().in("role_id", roleIds)).stream()
                .map(RolePermission::getPermissionId).collect(Collectors.toList());
        if (permIds.size() == 0) {
            return new ArrayList<>();
        }
        return permissionService.listByIds(permIds).stream()
                .map(Permission::getPermission).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        List<Integer> roleIds = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", loginId)).stream()
                .map(UserRole::getRoleId).collect(Collectors.toList());
        if (roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return roleService.listByIds(roleIds).stream()
                .map(Role::getName).collect(Collectors.toList());
    }
}
