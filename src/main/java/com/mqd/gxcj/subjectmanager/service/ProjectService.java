package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mqd.gxcj.subjectmanager.pojo.vo.AppProjectForm;

/**
 * <p>
 * 项目申报表，专家id会有1个以上，单独存储在project_expert表 服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface ProjectService extends IService<Project> {

    /**
     * 项目申报方法
     * @param projectForm 项目申报表单
     */
    boolean applicationProject(AppProjectForm projectForm) throws AppException;
}
