package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Menu;
import com.mqd.gxcj.subjectmanager.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.route.Route;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 获取当前所有的路由
     * @return 返回路由表
     */
    List<Route> getRoutes();

    List<Route> getRoutesByIds(List<Integer> id) throws AppException;
    List<Route> getRoutesById(Integer id) throws AppException;

    boolean addRoutesByRoleId(Integer id, List<Integer> menuIds) throws AppException;

    /**
     * 根据角色id获取路由列表
     * @param id    角色id
     * @return  路由列表
     */
    List<Menu> getMenuListByRoleId(Integer id);
}
