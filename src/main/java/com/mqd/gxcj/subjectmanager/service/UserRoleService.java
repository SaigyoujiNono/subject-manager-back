package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户-角色表，用户与角色的关系是n-1 服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface UserRoleService extends IService<UserRole> {
    List<String> getRoleList(String id);
}
