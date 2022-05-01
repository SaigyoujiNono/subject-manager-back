package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.vo.*;
import com.mqd.gxcj.subjectmanager.service.ProjectExpertizeService;
import com.mqd.gxcj.subjectmanager.service.ProjectService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 项目申报表，专家id会有1个以上，单独存储在project_expert表 前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "项目管理")
@RestController
@RequestMapping("/project")
@SaCheckLogin
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private ProjectExpertizeService projectExpertizeService;

    @ApiOperation(value = "项目申报")
    @PostMapping("/project")
    @SaCheckRole(value = {"principal"}, mode = SaMode.OR)
    public R applicationProject(@RequestBody @Validated AppProjectForm appProjectForm) throws AppException {
        System.out.println(appProjectForm);
        boolean b = projectService.applicationProject(appProjectForm);
        if (b){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "等待材料审核的项目列表")
    @GetMapping("/uncheckProject")
    public R uncheckProjectList(@Validated ProjectQuery queryForm,
                                @Validated AppPage appPage){
        QueryWrapper<Project> projectQuery = new QueryWrapper<>();
        List<Integer> subjectIds = queryForm.getSubjectIds();
        String name = queryForm.getName();
        if (StringUtils.hasText(name)){
            projectQuery.like("name", queryForm.getName());
        }
        if (subjectIds != null){
            projectQuery.in("subjectId", subjectIds);
        }
        if (queryForm.getStartTime() != null){
            projectQuery.ge("create_time",queryForm.getStartTime());
        }
        if (queryForm.getEndTime() != null) {
            projectQuery.le("create_time",queryForm.getEndTime());
        }

        projectQuery.eq("status", Project.UNCHECKED);

        IPage<Project> page = new Page<>(appPage.getCurrent(),appPage.getSize());
        projectService.page(page,projectQuery);
        BeanUtils.copyProperties(page,appPage);
        return R.ok().put("projectList",page.getRecords()).put("pageInfo",appPage);
    }
    
    @ApiOperation(value = "获取用户自己申请的项目")
    @GetMapping("/myProject")
    public R getMyProject(@Validated AppPage appPage) {
        Object userId = StpUtil.getLoginId();
        IPage<Project> page = new Page<>(appPage.getCurrent(),appPage.getSize());
        QueryWrapper<Project> projectQuery = new QueryWrapper<>();
        projectQuery.eq("principal",userId);
        projectService.page(page, projectQuery);
        BeanUtils.copyProperties(page,appPage);
        return R.ok().put("myProjectList", page.getRecords()).put("pageInfo",appPage);
    }

    @ApiOperation(value = "根据id获取项目的详细信息")
    @GetMapping("/project/{id}")
    public R getProjectDetail(@PathVariable String id) throws AppException {
        ProjectDetail projectDetailById = projectService.getProjectDetailById(id);
        return R.ok().put("projectDetail", projectDetailById);
    }

    @ApiOperation(value = "材料审核的接口")
    @PostMapping("/checkProject")
    public R checkProject(@RequestBody CheckProjectForm form) throws AppException {
        boolean b = projectService.checkProjectByMaterial(form);
        if (b) {
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value= "根据用户id获取正在等待专家评审的项目")
    @GetMapping("/checkingByExpert")
    public R checkingByExpert(AppPage appPage) {
        String userId = (String) StpUtil.getLoginId();
        IPage<Project> page = projectExpertizeService.getCheckingProjectByUserId(userId, appPage);
        BeanUtils.copyProperties(page, appPage);
        return R.ok().put("projects", page.getRecords()).put("pageInfo", appPage);
    }
}
