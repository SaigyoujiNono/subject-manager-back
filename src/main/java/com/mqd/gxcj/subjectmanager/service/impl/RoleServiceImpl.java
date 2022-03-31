package com.mqd.gxcj.subjectmanager.service.impl;

import com.mqd.gxcj.subjectmanager.pojo.Role;
import com.mqd.gxcj.subjectmanager.mapper.RoleMapper;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表，主要用户存储用户的身份信息 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
