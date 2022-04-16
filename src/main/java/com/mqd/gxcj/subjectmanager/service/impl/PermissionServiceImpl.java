package com.mqd.gxcj.subjectmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Permission;
import com.mqd.gxcj.subjectmanager.mapper.PermissionMapper;
import com.mqd.gxcj.subjectmanager.pojo.Role;
import com.mqd.gxcj.subjectmanager.pojo.RolePermission;
import com.mqd.gxcj.subjectmanager.pojo.vo.RolePermForm;
import com.mqd.gxcj.subjectmanager.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.service.RolePermissionService;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-05-01
 */
@Service
@CacheConfig(cacheNames = "perm")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Cacheable(key = "#roleId")
    @Override
    public List<Permission> getPermissionByRoleId(Integer roleId) {
        return baseMapper.getPermsByRoleId(roleId);
    }

    @CacheEvict(key = "#rolePermForm.roleId")
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean saveRolePerms(RolePermForm rolePermForm) throws AppException {
        Integer roleId = rolePermForm.getRoleId();
        List<Integer> permIds = rolePermForm.getPermIds();
        // 检查角色是否存在
        Role byId = roleService.getById(roleId);
        if (byId == null) {
            throw new AppException(RStatus.ROLE_NOT_EXIST);
        }

        // 检查权限是否存在
        List<Permission> permissions = baseMapper.selectBatchIds(permIds);
        if (permissions.size() != permIds.size()) {
            throw new AppException(RStatus.PERMISSION_NOT_EXIST);
        }

        // 删除原有权限
        rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("role_id", roleId));

        List<RolePermission> collect = permIds.stream()
                .map(el -> new RolePermission().setPermissionId(el).setRoleId(roleId))
                .collect(Collectors.toList());
        return rolePermissionService.saveBatch(collect);
    }

    @Cacheable(key = "'all'")
    @Override
    public List<Permission> list() {
        return super.list();
    }

    @CacheEvict(key = "'all'")
    @Override
    public boolean save(Permission entity) {
        return super.save(entity);
    }
}
