package com.mqd.gxcj.subjectmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqd.gxcj.subjectmanager.pojo.ProjectExpenditure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mqd.gxcj.subjectmanager.pojo.vo.ExpenditureAndProjectVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 经费申请表 Mapper 接口
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
public interface ProjectExpenditureMapper extends BaseMapper<ProjectExpenditure> {

    IPage<ExpenditureAndProjectVo> getProjectList(IPage<ExpenditureAndProjectVo> page, @Param("ew") QueryWrapper<ProjectExpenditure> query);
}
