package com.mqd.gxcj.subjectmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.User;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppPage;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserModifyForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserQuery;
import com.mqd.gxcj.subjectmanager.service.UserService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "用户信息管理")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "添加一个新用户")
    @PostMapping("/user")
    public R addUser(@RequestBody @Validated UserForm userForm) throws AppException {
        if (userService.saveUser(userForm)){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/user")
    public R getUserList(UserQuery userQuery,@Validated AppPage appPage){
        IPage<User> userPage = userService.getUserListByQuery(userQuery, appPage);
        BeanUtils.copyProperties(userPage, appPage);
        return R.ok().put("users", userPage.getRecords()).put("pageInfo", appPage);
    }

    @ApiOperation(value = "用户自己修改信息")
    @PostMapping("/modifySelf")
    public R modifyUserSelf(@Validated UserModifyForm modifyForm) throws AppException {
        if (userService.modifyUserSelf(modifyForm)){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "获取待审核的用户列表")
    @GetMapping("/modifySelf")
    public R getUserSelfList(){
        return R.ok();
    }

    @ApiOperation(value = "对用户信息修改的审核")
    @PutMapping("/modifySelf")
    public R checkUserSelf(){
        return R.ok();
    }

    @ApiOperation(value = "项目申报时提供可选择的列表")
    @GetMapping("/listOnMember")
    public R listOnProjectMember(Integer subjectId,@Validated AppPage appPage){
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.eq("subject_id", subjectId);
        userQuery.eq("role_id",2);
        IPage<User> page = new Page<>(appPage.getCurrent(), appPage.getSize());
        userService.listOnProjectMember(page,userQuery);
        BeanUtils.copyProperties(page, appPage);
        return R.ok().put("listMember",page.getRecords()).put("pageInfo",appPage);
    }

    @ApiOperation(value = "可供选择的专家列表")
    @GetMapping("/listOnExpert")
    public R listOnExpertList(String name, @Validated AppPage appPage) {
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.eq("role_id",3);
        if (StringUtils.hasText(name)) {
            userQuery.likeRight("name",name);
        }
        IPage<User> page = new Page<>(appPage.getCurrent(), appPage.getSize());
        userService.listOnProjectMember(page,userQuery);
        BeanUtils.copyProperties(page, appPage);
        return R.ok().put("listExpert",page.getRecords()).put("pageInfo",appPage);
    }

}
