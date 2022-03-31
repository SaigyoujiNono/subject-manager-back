package com.mqd.gxcj.subjectmanager.mapper;

import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface ProjectExpertizeMapper extends BaseMapper<ProjectExpertize> {

}
