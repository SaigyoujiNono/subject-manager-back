package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Menu;
import com.mqd.gxcj.subjectmanager.pojo.RoleMenu;
import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.mqd.gxcj.subjectmanager.pojo.route.Route;
import com.mqd.gxcj.subjectmanager.pojo.vo.RoleMenuForm;
import com.mqd.gxcj.subjectmanager.service.RoleMenuService;
import com.mqd.gxcj.subjectmanager.service.UserRoleService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "路由控制接口")
@RestController
@RequestMapping("/roleMenu")
@SaCheckLogin
public class RoleMenuController {
    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private UserRoleService userRoleService;

    @ApiOperation(value = "获取所有路由表")
    @GetMapping("/getRouter")
    public R getRouter() {
        List<Route> routes = roleMenuService.getRoutes();
        return R.ok().put("routes", routes);
    }

    @ApiOperation(value = "获取用户拥有的所有路由表")
    @GetMapping("/getMyRouter")
    public R getUserRouter() throws AppException {
        QueryWrapper<UserRole> userRoleQuery = new QueryWrapper<>();
        userRoleQuery.eq("user_id", StpUtil.getLoginId());
        List<String> roleList = StpUtil.getRoleList();
        List<Route> routesByIds;
        if (roleList.stream().anyMatch(el -> el.equals("admin"))) {
            routesByIds = roleMenuService.getRoutes();
        }else {
            List<UserRole> list = userRoleService.list(userRoleQuery);
            List<Integer> collect = list.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            routesByIds = roleMenuService.getRoutesByIds(collect);
        }
        return R.ok().put("routes", routesByIds);
    }

    @ApiOperation(value = "根据角色id获取角色所拥有的路由表")
    @GetMapping("/routes")
    public R getRoutesByRoleId(@NotNull Integer id) throws AppException {
        List<Route> routes = roleMenuService.getRoutesById(id);
        return R.ok().put("routes", routes);
    }

    @ApiOperation(value = "根据角色id获取角色所拥有的路由列表")
    @GetMapping("/routeList")
    public R getRouteListByRoleId(@NotNull Integer id) {
        List<Menu> routes = roleMenuService.getMenuListByRoleId(id);
        return R.ok().put("routes", routes);
    }

    @ApiOperation(value = "根据角色id设置角色所拥有的路由表")
    @PostMapping("/routes")
    public R addRoutesById(@RequestBody @Validated RoleMenuForm roleMenuForm) throws AppException {
        if (roleMenuService.addRoutesByRoleId(roleMenuForm.getId(), roleMenuForm.getMenuIds())){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }
}
