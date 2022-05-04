package com.mqd.gxcj.subjectmanager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpenditure;
import com.mqd.gxcj.subjectmanager.mapper.ProjectExpenditureMapper;
import com.mqd.gxcj.subjectmanager.pojo.vo.CheckExpenditureForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.ProjectExpenditureForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UpdateProjectExpenditureForm;
import com.mqd.gxcj.subjectmanager.service.ProjectExpenditureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.service.ProjectService;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 经费申请表 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Service
public class ProjectExpenditureServiceImpl extends ServiceImpl<ProjectExpenditureMapper, ProjectExpenditure> implements ProjectExpenditureService {

    @Resource
    private ProjectService projectService;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean applicationExpenditure(ProjectExpenditureForm projectExpenditureForm) throws AppException {
        // 先判断项目是否存在
        String projectId = projectExpenditureForm.getProjectId();
        Project project = projectService.getById(projectId);
        if (project == null) {
            throw new AppException(RStatus.PROJECT_NOT_EXIST);
        }
        // 判断项目状态，只有处于立项状态的项目才可以申请经费
        String status = project.getStatus();
        if(!status.equals(Project.COMMITTED)) {
            throw new AppException(RStatus.PROJECT_STATUS_ERROR);
        }
        ProjectExpenditure projectExpenditure = new ProjectExpenditure();
        // 将表单数据复制到数据对象
        BeanUtils.copyProperties(projectExpenditureForm, projectExpenditure);
        // 放入固定数据
        projectExpenditure.setCommittedTime(LocalDateTime.now())
                .setCheckStatus(ProjectExpenditure.COMMITTED)
                .setApplicationUserId(StpUtil.getLoginIdAsString());
        // 入库
        return baseMapper.insert(projectExpenditure) > 0;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean checkExpenditure(CheckExpenditureForm checkExpenditureForm) throws AppException {
        Integer id = checkExpenditureForm.getId();
        String opinion = checkExpenditureForm.getOpinion();
        // 先判断经费id是否存在于数据库
        ProjectExpenditure projectExpenditure = baseMapper.selectById(id);
        if (projectExpenditure == null) {
            throw new AppException(RStatus.EXPENDITURE_NOT_EXIST);
        }
        // 判断是否为提交状态
        String checkStatus = projectExpenditure.getCheckStatus();
        if (!checkStatus.equals(ProjectExpenditure.COMMITTED)) {
            throw new AppException(RStatus.EXPENDITURE_STATUS_ERROR);
        }

        // 如果意见为空代表同意经费申请
        if (!StringUtils.hasText(opinion)) {
            projectExpenditure.setChecker(StpUtil.getLoginIdAsString())
                    .setCheckTime(LocalDateTime.now())
                    .setCheckStatus(ProjectExpenditure.CHECKED);
            return baseMapper.updateById(projectExpenditure) > 0;
        }
        // 如果意见不为空代表不通过审核
        projectExpenditure.setChecker(StpUtil.getLoginIdAsString())
                .setCheckTime(LocalDateTime.now())
                .setCheckStatus(ProjectExpenditure.NO_CHECKED)
                .setNoCheckedOpinion(opinion);

        return baseMapper.updateById(projectExpenditure) > 0;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean updateProjectExpenditure(UpdateProjectExpenditureForm projectExpenditureForm) throws AppException {
        Integer id = projectExpenditureForm.getId();
        // 先判断经费单是否存在
        ProjectExpenditure projectExpenditure = baseMapper.selectById(id);
        if (projectExpenditure == null) {
            throw new AppException(RStatus.EXPENDITURE_NOT_EXIST);
        }
        // 检查状态是否为审核未通过
        String checkStatus = projectExpenditure.getCheckStatus();
        if (!checkStatus.equals(ProjectExpenditure.NO_CHECKED)) {
            throw new AppException(RStatus.EXPENDITURE_STATUS_ERROR);
        }
        ProjectExpenditure pe = new ProjectExpenditure();
        BeanUtils.copyProperties(projectExpenditure, pe);
        // 设置经费单信息为默认
        pe.setNoCheckedOpinion("")
                .setCheckTime(null)
                .setCheckStatus(ProjectExpenditure.COMMITTED);
        baseMapper.updateById(pe);
        return true;
    }
}
