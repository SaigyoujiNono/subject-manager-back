package com.mqd.gxcj.subjectmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mqd.gxcj.subjectmanager.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表，用于存储用户的基本信息 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户信息
     */
    UserVo getUserInfo(String id);

    /**
     * 获取待项目选择的成员列表
     */
    IPage<User> listOnProjectMember(IPage<User> page, @Param("ew") QueryWrapper<User> queryWrapper);
}
