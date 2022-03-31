package com.mqd.gxcj.subjectmanager.service.impl;

import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.mqd.gxcj.subjectmanager.mapper.UserRoleMapper;
import com.mqd.gxcj.subjectmanager.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户-角色表，用户与角色的关系是n-1 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@CacheConfig(cacheNames = "user-role-list")
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Cacheable(key = "#id")
    @Override
    public List<String> getRoleList(String id) {
        return baseMapper.getRoleList(id);
    }
}
