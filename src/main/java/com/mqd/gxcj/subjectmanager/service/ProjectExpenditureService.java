package com.mqd.gxcj.subjectmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpenditure;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.vo.CheckExpenditureForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.ExpenditureAndProjectVo;
import com.mqd.gxcj.subjectmanager.pojo.vo.ProjectExpenditureForm;
import com.mqd.gxcj.subjectmanager.pojo.vo.UpdateProjectExpenditureForm;

/**
 * <p>
 * 经费申请表 服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
public interface ProjectExpenditureService extends IService<ProjectExpenditure> {

    /**
     * 申请经费操作
     * @param projectExpenditureForm  经费表单
     * @return  true提交成功
     */
    boolean applicationExpenditure(ProjectExpenditureForm projectExpenditureForm) throws AppException;

    /**
     * 审查经费申请
     */
    boolean checkExpenditure(CheckExpenditureForm checkExpenditureForm) throws AppException;

    /**
     * 修改经费审核，只有审核未通过的经费单才可以修改
     */
    boolean updateProjectExpenditure(UpdateProjectExpenditureForm projectExpenditureForm) throws AppException;

    /**
     * 获取项目列表
     */
    IPage<ExpenditureAndProjectVo> getProjectList(IPage<ExpenditureAndProjectVo> page, QueryWrapper<ProjectExpenditure> query);
}
