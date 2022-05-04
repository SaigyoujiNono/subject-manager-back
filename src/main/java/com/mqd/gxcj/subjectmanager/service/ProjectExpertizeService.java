package com.mqd.gxcj.subjectmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.dto.ExpertOpinion;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppPage;

import java.util.List;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表 服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface ProjectExpertizeService extends IService<ProjectExpertize> {

    /**
     * 获取专家意见以及专家的信息
     */
    List<ExpertOpinion> getExpertOpinion(QueryWrapper<ProjectExpertize> queryWrapper);

    /**
     * 根据专家id获取待该专家审核的id
     * @param userId    用户id
     * @param appPage   分页信息
     * @return 项目列表
     */
    IPage<Project> getCheckingProjectByUserId(String userId, AppPage appPage);

}
