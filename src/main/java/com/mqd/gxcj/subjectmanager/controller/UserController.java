package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
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
        boolean save = userService.saveUser(userForm);
        if (save){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/user")
    public R getUserList(UserQuery userQuery, AppPage appPage){

        return R.ok();
    }

    @ApiOperation(value = "用户自己修改信息")
    @PostMapping("/modifySelf")
    public R modifyUserSelf(UserModifyForm modifyForm) throws AppException {
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

}
