package com.mqd.gxcj.subjectmanager.mapper;

import com.mqd.gxcj.subjectmanager.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserVo;

/**
 * <p>
 * 用户表，用于存储用户的基本信息 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface UserMapper extends BaseMapper<User> {

    UserVo getUserInfo(String id);
}
