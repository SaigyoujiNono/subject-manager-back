package com.mqd.gxcj.subjectmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.mqd.gxcj.subjectmanager.mapper.ProjectExpertizeMapper;
import com.mqd.gxcj.subjectmanager.pojo.dto.ExpertOpinion;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppPage;
import com.mqd.gxcj.subjectmanager.service.ProjectExpertizeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private ProjectService projectService;

    @Override
    public List<ExpertOpinion> getExpertOpinion(QueryWrapper<ProjectExpertize> queryWrapper) {
        return baseMapper.getExpertOpinionAndUserInfo(queryWrapper);
    }

    @Override
    public IPage<Project> getCheckingProjectByUserId(String userId, AppPage appPage) {
        // 先获取项目id
        QueryWrapper<ProjectExpertize> peQuery = new QueryWrapper<>();
        peQuery.eq("user_id", userId);
        IPage<ProjectExpertize> pePage = new Page<>(appPage.getCurrent(), appPage.getSize());
        baseMapper.selectPage(pePage, peQuery);
        List<String> collect = pePage.getRecords().stream().map(ProjectExpertize::getProjectId).collect(Collectors.toList());

        // 根据id查询项目
        IPage<Project> page = new Page<>();
        BeanUtils.copyProperties(pePage, page);
        if (collect.size() == 0) {
            return page;
        }
        page.setRecords(projectService.listByIds(collect));
        return page;
    }
}
