package com.mqd.gxcj.subjectmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.vo.*;

/**
 * <p>
 * 用户表，用于存储用户的基本信息 服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface UserService extends IService<User> {
    /**
     * 获取用户信息
     * @param id    用户id
     * @return  返回经过处理的用户信息
     */
    UserVo getUserInfo(String id);

    /**
     * 存入用户信息，需要传入用户的角色信息
     * @param userForm  添加用户的表单
     */
    boolean saveUser(UserForm userForm) throws AppException;

    /**
     * 用户自己修改个人信息，需要审核
     */
    boolean modifyUserSelf(UserModifyForm userModify) throws AppException;

    /**
     * 获取待项目选择的成员列表
     */
    IPage<User> listOnProjectMember(IPage<User> page, QueryWrapper<User> queryWrapper);

    /**
     * 可供选择的专家列表
     */
    IPage<User> listOnProjectExpert(IPage<User> page, QueryWrapper<User> queryWrapper);

    /**
     * 根据条件获取用户列表
     * @param userQuery 查询条件
     * @param appPage   页面信息
     * @return  page
     */
    IPage<User> getUserListByQuery(UserQuery userQuery, AppPage appPage);
}
