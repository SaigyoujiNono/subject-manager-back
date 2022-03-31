package com.mqd.gxcj.subjectmanager.service.impl;

import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.mqd.gxcj.subjectmanager.mapper.ProjectExpertizeMapper;
import com.mqd.gxcj.subjectmanager.service.ProjectExpertizeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Service
public class ProjectExpertizeServiceImpl extends ServiceImpl<ProjectExpertizeMapper, ProjectExpertize> implements ProjectExpertizeService {

}