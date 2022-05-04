package com.mqd.gxcj.subjectmanager.service;


import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;

import java.util.Map;

/**
 * 工具服务类，提供一些方便的接口
 */
public interface UtilsService {

    /**
     * 获取一些不经常改变的常用信息类
     */
    RelevanceInfo getRelevanceInfo();

    /**
     * 返回首页的统计信息
     */
    Map<String, Object> homeStatistic();
}
