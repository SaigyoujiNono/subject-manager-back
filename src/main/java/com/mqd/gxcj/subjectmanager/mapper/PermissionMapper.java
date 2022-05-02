package com.mqd.gxcj.subjectmanager.mapper;

import com.mqd.gxcj.subjectmanager.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermsByRoleId(Integer roleId);
}
