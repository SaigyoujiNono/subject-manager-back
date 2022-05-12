package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpenditure;
import com.mqd.gxcj.subjectmanager.pojo.vo.*;
import com.mqd.gxcj.subjectmanager.service.ProjectExpenditureService;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 经费申请表 前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Api(tags = "项目经费管理")
@RestController
@RequestMapping("/projectExpenditure")
@SaCheckLogin
public class ProjectExpenditureController {

    @Resource
    private ProjectExpenditureService projectExpenditureService;

    @ApiOperation(value = "申请项目经费")
    @PostMapping("/application")
    @SaCheckPermission(value = "expenditure:application")
    public R applicationExpenditure(@RequestBody @Validated ProjectExpenditureForm projectExpenditureForm) throws AppException {
        if (projectExpenditureService.applicationExpenditure(projectExpenditureForm)){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "项目经费列表")
    @GetMapping("/expenditureList")
    public R getExpenditureList(ExpenditureQuery expenditureQuery, AppPage appPage){
        QueryWrapper<ProjectExpenditure> query = getProjectExpenditureQueryWrapper(expenditureQuery);

        IPage<ExpenditureAndProjectVo> page = new Page<>(appPage.getCurrent(), appPage.getSize());
        page = projectExpenditureService.getProjectList(page, query);
        BeanUtils.copyProperties(page, appPage);
        return R.ok().put("pageInfo", appPage).put("expenditureList", page.getRecords());
    }

    @ApiOperation(value = "经费审核")
    @PostMapping("checkExpenditure")
    @SaCheckPermission(value = "expenditure:check")
    public R checkExpenditure(@RequestBody @Validated CheckExpenditureForm checkExpenditureForm) throws AppException {
        if (projectExpenditureService.checkExpenditure(checkExpenditureForm)){
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    @ApiOperation(value = "修改经费单信息")
    @PutMapping("/update")
    @SaCheckPermission(value = "expenditure:application")
    public R updateExpenditure(@RequestBody @Validated UpdateProjectExpenditureForm projectExpenditureForm) throws AppException {
        if(projectExpenditureService.updateProjectExpenditure(projectExpenditureForm)) {
            return R.ok();
        }
        throw new AppException(RStatus.ERROR);
    }

    /**
     * 构建查询条件
     */
    private QueryWrapper<ProjectExpenditure> getProjectExpenditureQueryWrapper(ExpenditureQuery expenditureQuery) {
        QueryWrapper<ProjectExpenditure> query = new QueryWrapper<>();
        // 构建查询条件
        String name = expenditureQuery.getName();
        String checkStatus = expenditureQuery.getCheckStatus();
        LocalDateTime minCommittedTime = expenditureQuery.getMinCommittedTime();
        LocalDateTime maxCommittedTime = expenditureQuery.getMaxCommittedTime();
        BigDecimal minExpenditure = expenditureQuery.getMinExpenditure();
        BigDecimal maxExpenditure = expenditureQuery.getMaxExpenditure();
        String userId = expenditureQuery.getUserId();
        if (StringUtils.hasText(name)) {
            query.like("name", name);
        }
        if (StringUtils.hasText(checkStatus)) {
            query.eq("check_status", checkStatus);
        }
        if (minCommittedTime != null) {
            query.ge("committed_time", minCommittedTime);
        }
        if (maxCommittedTime != null) {
            query.le("committed_time", maxCommittedTime);
        }
        if (minExpenditure != null) {
            query.ge("expenditure", minExpenditure);
        }
        if (maxExpenditure != null) {
            query.le("expenditure", maxExpenditure);
        }
        if (StringUtils.hasText(userId)) {
            query.eq("application_user_id", userId);
        }
        return query;
    }
}
