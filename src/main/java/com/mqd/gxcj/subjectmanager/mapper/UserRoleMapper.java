package com.mqd.gxcj.subjectmanager.mapper;

import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户-角色表，用户与角色的关系是n-1 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据id获取用户角色列表
     */
    List<String> getRoleList(String id);
}
