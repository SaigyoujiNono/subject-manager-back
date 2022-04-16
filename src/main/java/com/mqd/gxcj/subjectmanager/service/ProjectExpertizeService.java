package com.mqd.gxcj.subjectmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.dto.ExpertOpinion;

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
}
