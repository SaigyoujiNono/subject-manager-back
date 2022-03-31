package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppPage;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppProjectForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.CheckProjectQuery;
import com.mqd.gxcj.subjectmanager.service.ProjectService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class ProjectController {

    @Resource
    private ProjectService projectService;

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

    @ApiModelProperty(value = "等待材料审核的项目列表")
    @GetMapping("/uncheckProject")
    public R uncheckProjectList(@Validated CheckProjectQuery queryForm,
                                @Validated AppPage appPage){
        QueryWrapper<Project> projectQuery = new QueryWrapper<>();
        Integer subjectId = queryForm.getSubjectId();
        String name = queryForm.getName();
        if (StringUtils.hasText(name)){
            projectQuery.like("name", queryForm.getName());
        }
        if (subjectId != null){
            projectQuery.eq("subjectId", subjectId);
        }
        projectQuery.eq("status", Project.UNCHECKED);

        IPage<Project> page = new Page<>(appPage.getCurrent(),appPage.getSize());
        projectService.page(page,projectQuery);
        return R.ok();
    }
}
