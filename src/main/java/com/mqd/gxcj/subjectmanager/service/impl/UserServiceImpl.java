package com.mqd.gxcj.subjectmanager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.mapper.*;
import com.mqd.gxcj.subjectmanager.pojo.*;
import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserModifyForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserVo;
import com.mqd.gxcj.subjectmanager.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.utils.MD5Utils;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 * 用户表，用于存储用户的基本信息 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@CacheConfig(cacheNames = "user-info")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AccountService accountService;

    @Resource
    private UtilsService utilsService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserModifyService userModifyService;

    @Override
    public UserVo getUserInfo(String id) {
        return baseMapper.getUserInfo(id);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean saveUser(UserForm userForm) throws AppException {
        //获取常规信息
        RelevanceInfo relevanceInfo = utilsService.getRelevanceInfo();
        //先查询用户提交的角色、学科、职务、职称、学历ID是否存在，如果返回为false则抛出参数异常
        if (!relevanceInfo.hasAll(userForm.getSubjectId(), userForm.getRole(), userForm.getDuty(),
                userForm.getRank(), userForm.getEducation())){
            throw new AppException(RStatus.VERIFY_ERROR);
        }

        //检查用户Id是否存在，如果存在则抛出账户已存在异常
        Account account1 = accountService.getById(userForm.getId());
        if (account1 != null){
            throw new AppException(RStatus.ACCOUNT_EXIST);
        }

        //新添加用户默认密码为123456，对密码进行MD5加密，盐值为用户的id
        String id = userForm.getId();
        String pwd = MD5Utils.MD5Lower("123456", id);
        //加密完成后构建Account对象，存入数据库
        Account account = new Account().setId(id).setPassword(pwd);
        //插入账户表
        accountService.save(account);
        //将表单的数据赋值到user对象
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        //插入用户信息表
        baseMapper.insert(user);
        //插入用户-角色表
        UserRole userRole = new UserRole()
                .setUserId(id)
                .setRoleId(userForm.getRole());
        userRoleService.save(userRole);
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean modifyUserSelf(UserModifyForm userModifyForm) throws AppException {
        //获取常规信息
        RelevanceInfo ri = utilsService.getRelevanceInfo();
        //先查询用户提交的角色、学科、职务、职称、学历ID是否存在，如果返回为false则抛出参数异常
        if (!(ri.hasSubject(userModifyForm.getSubjectId()) &&
                ri.hasDuty(userModifyForm.getDuty()) &&
                ri.hasEducation(userModifyForm.getEducation()) &&
                ri.hasRank(userModifyForm.getRank()))
        ){
            throw new AppException(RStatus.VERIFY_ERROR);
        }
        UserModify userModify = new UserModify();
        //将当前登录用户的id填入userModify
        userModify.setUserId((String) StpUtil.getLoginId());
        BeanUtils.copyProperties(userModifyForm, userModify);
        //将状态置为已提交
        userModify.setStatus(UserModify.COMMITTED);
        userModifyService.save(userModify);
        return true;
    }

    @Cacheable(key = "#id")
    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }
}
