package com.mqd.gxcj.subjectmanager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Account;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.mqd.gxcj.subjectmanager.mapper.ProjectMapper;
import com.mqd.gxcj.subjectmanager.pojo.ProjectUser;
import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppProjectForm;
import com.mqd.gxcj.subjectmanager.service.AccountService;
import com.mqd.gxcj.subjectmanager.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.service.ProjectUserService;
import com.mqd.gxcj.subjectmanager.service.UtilsService;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目申报表，专家id会有1个以上，单独存储在project_expert表 服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private AccountService accountService;

    @Resource
    private UtilsService utilsService;

    @Resource
    private ProjectUserService projectUserService;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public boolean applicationProject(AppProjectForm projectForm) throws AppException {
        //先检查提交的项目负责人与登录人员是否一致
        String loginId = (String)StpUtil.getLoginId();
        if (!loginId.equals(projectForm.getPrincipal())) {
            throw new AppException(RStatus.VERIFY_ERROR);
        }
        // 检测最终验收日期是否符合标准
        LocalDate finalReviewTime = projectForm.getFinalReviewTime();
        int isPass = finalReviewTime.compareTo(LocalDate.now());
        if (isPass <= 0){
            throw new AppException(RStatus.VERIFY_ERROR);
        }
        //获取项目其他参与人员
        List<String> member = projectForm.getMember();
        List<ProjectUser> projectUsers = new ArrayList<>();
        //如果项目参与人员有其他人
        if ( member!=null ){
            //如果项目参与人员大于三人，或者项目参与人员与负责人id重合了，则返回参数校验异常
            if ( member.size() > 3 &&
                    member.stream().anyMatch(
                            el -> el.equals(projectForm.getPrincipal()))){
                throw new AppException(RStatus.VERIFY_ERROR);
            }
            //当项目参与人员的id在数据库中不存在的时候同样返回参数校验异常
            QueryWrapper<Account> accountQuery = new QueryWrapper<>();
            accountQuery.in("id", member);
            List<Account> list = accountService.list(accountQuery);
            if (list.size() != member.size()){
                throw new AppException(RStatus.VERIFY_ERROR);
            }
            member.forEach(n->projectUsers.add(new ProjectUser().setUserId(n)));
        }
        //查询项目名称是否重名
        QueryWrapper<Project> projectQuery = new QueryWrapper<>();
        projectQuery.eq("name",projectForm.getName());
        Long aLong = baseMapper.selectCount(projectQuery);
        //如果大于0则代表项目名称已存在，抛出项目已存在异常
        if (aLong > 0){
            throw new AppException(RStatus.PROJECT_EXIST);
        }
        //判断项目所属学科是否存在，不存在则返回参数校验异常
        RelevanceInfo relevanceInfo = utilsService.getRelevanceInfo();
        if( !relevanceInfo.hasSubject(projectForm.getSubjectId()) ){
            throw new AppException(RStatus.VERIFY_ERROR);
        }
        //校验完毕没有问题后插入数据库，进入材料审核流程
        Project project = new Project();
        BeanUtils.copyProperties(projectForm, project);
        project.setStatus(Project.UNCHECKED);
        project.setChecked(Project.UNCHECKED);
        baseMapper.insert(project);
        if (projectUsers.size() > 0){
            projectUsers.forEach(el -> el.setProjectId(project.getId()));
            projectUserService.saveBatch(projectUsers);
        }
        return true;
    }
}
