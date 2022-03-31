package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserModifyForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserVo;

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
}
