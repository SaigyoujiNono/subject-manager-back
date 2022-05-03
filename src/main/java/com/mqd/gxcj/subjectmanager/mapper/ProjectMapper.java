package com.mqd.gxcj.subjectmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.pojo.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 项目申报表，专家id会有1个以上，单独存储在project_expert表 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface ProjectMapper extends BaseMapper<Project> {

    /**
     * 获取用户自己的项目列表
     * @param page
     * @param projectQuery
     * @return
     */
    IPage<Project> pageMyProjectList(IPage<Project> page, @Param("ew")  QueryWrapper<Project> projectQuery);
}
