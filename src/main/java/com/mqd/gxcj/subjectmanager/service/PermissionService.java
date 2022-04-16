package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.vo.RolePermForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getPermissionByRoleId(Integer roleId);

    boolean saveRolePerms(RolePermForm rolePermForm) throws AppException;
}
