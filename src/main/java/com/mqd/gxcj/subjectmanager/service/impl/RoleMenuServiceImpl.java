package com.mqd.gxcj.subjectmanager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.*;
import com.mqd.gxcj.subjectmanager.mapper.RoleMenuMapper;
import com.mqd.gxcj.subjectmanager.pojo.route.Meta;
import com.mqd.gxcj.subjectmanager.pojo.route.Route;
import com.mqd.gxcj.subjectmanager.service.MenuService;
import com.mqd.gxcj.subjectmanager.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Service
@CacheConfig(cacheNames = "menu")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMenuService roleMenuService;

    @Cacheable(key = "'menu'")
    @Override
    public List<Route> getRoutes() {
        // 直接获取所有菜单
        List<Menu> list = menuService.list();
        return generateRoutes(list, 0);
    }

//    @Cacheable(keyGenerator = "userIdKeyGenerator")
    @Override
    public List<Route> getRoutesByIds(List<Integer> ids) throws AppException {

        QueryWrapper<RoleMenu> roleMenuQuery = new QueryWrapper<>();
        roleMenuQuery.in("role_id", ids);
        List<RoleMenu> roleMenus = baseMapper.selectList(roleMenuQuery);

        if (roleMenus == null || roleMenus.size() == 0) {
            throw new AppException(RStatus.ERROR);
        }

        List<Integer> collect = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuService.listByIds(collect);

        return generateRoutes(menus, 0);
    }

    @Cacheable(key = "'role_'+#id")
    @Override
    public List<Route> getRoutesById(Integer id) throws AppException {
        return generateRoutes(getMenuListByRoleId(id), 0);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @CacheEvict(key = "'role_'+#id")
    @Override
    public boolean addRoutesByRoleId(Integer id, List<Integer> menuIds) throws AppException {
        // 检查角色是否一致
        Role role = roleService.getById(id);
        if (role == null) {
            throw new AppException(RStatus.ROLE_NOT_EXIST);
        }
        // 检查菜单是否一致
        List<Menu> menus = menuService.listByIds(menuIds);
        if (menus.size() != menuIds.size()) {
            throw new AppException(RStatus.MENU_NOT_EXIST);
        }

        // 删除该角色所有的路由表
        QueryWrapper<RoleMenu> roleMenuQuery = new QueryWrapper<>();
        roleMenuQuery.in("role_id", id);
        baseMapper.delete(roleMenuQuery);

        List<RoleMenu> collect = menuIds.stream()
                .map(el -> new RoleMenu().setRoleId(id).setMenuId(el))
                .collect(Collectors.toList());
        return  saveBatch(collect);
    }

    @Override
    public List<Menu> getMenuListByRoleId(Integer id) {
        QueryWrapper<RoleMenu> roleMenuQuery = new QueryWrapper<>();
        roleMenuQuery.in("role_id", id);
        List<RoleMenu> list = roleMenuService.list(roleMenuQuery);
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> collect = list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        return menuService.listByIds(collect);
    }


    private List<Route> generateRoutes(List<Menu>  menuList, int parent) {
        List<Route> list = menuList.stream().filter(el -> el.getParent() == parent)
                .map(el -> {
            Route route = new Route();
            Meta meta = new Meta();
            BeanUtils.copyProperties(el, route);
            BeanUtils.copyProperties(el, meta);
            route.setMeta(meta);
            return route;
        }).collect(Collectors.toList());
        if (list.size() <= 0) {
            return list;
        }

        return list.stream().map(el -> el.setChildren(generateRoutes(menuList, el.getId())))
                .collect(Collectors.toList());
    }
}
