package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Account;
import com.mqd.gxcj.subjectmanager.pojo.User;
import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;
import com.mqd.gxcj.subjectmanager.pojo.vo.LoginForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserVo;
import com.mqd.gxcj.subjectmanager.service.AccountService;
import com.mqd.gxcj.subjectmanager.service.UserService;
import com.mqd.gxcj.subjectmanager.service.UtilsService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "用户行为管理")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private UserService userService;

    @Resource
    private UtilsService utilsService;

    @ApiOperation(value = "用户登录，需要传入账户、密码、rememberMe参数")
    @PostMapping("/login")
    public R login(@RequestBody LoginForm loginForm) throws AppException {
        Account account = new Account()
                .setId(loginForm.getUsername())
                .setPassword(loginForm.getPassword());
        // 判断用户是否合法、密码是否正确
        if (accountService.login(account)){
            StpUtil.login(account.getId(),loginForm.getRememberMe());
            // 登录成功返回成功信息
            return R.ok();
        }
        // 登录失败抛出登录失败异常
        throw new AppException(RStatus.LOGIN_FAILED);
    }

    @ApiOperation(value = "判断用户是否是登录状态，如果不是则返回错误信息，如果是则返回用户个人信息与权限信息")
    @GetMapping("/isLogin")
    @SaCheckLogin
    public R isLogin(){
        String loginId = (String)StpUtil.getLoginId();
        User userInfo = userService.getById(loginId);
        List<String> roleList = StpUtil.getRoleList();
        RelevanceInfo relevanceInfo = utilsService.getRelevanceInfo();
        return R.ok().put("userInfo",userInfo)
                .put("roleList",roleList)
                .put("baseInfo",relevanceInfo)
                .put("permission", StpUtil.getPermissionList());
    }

    @ApiOperation(value = "注销账户")
    @PostMapping("/logout")
    @SaCheckLogin
    public R logout(){
        StpUtil.logout();
        return R.ok();
    }
}
