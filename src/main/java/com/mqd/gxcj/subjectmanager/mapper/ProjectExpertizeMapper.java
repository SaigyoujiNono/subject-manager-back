package com.mqd.gxcj.subjectmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpertize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mqd.gxcj.subjectmanager.pojo.dto.ExpertOpinion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface ProjectExpertizeMapper extends BaseMapper<ProjectExpertize> {

    /**
     * 获取专家意见与专家信息
     */
    List<ExpertOpinion> getExpertOpinionAndUserInfo(@Param("ew") QueryWrapper<ProjectExpertize> queryWrapper);
}
