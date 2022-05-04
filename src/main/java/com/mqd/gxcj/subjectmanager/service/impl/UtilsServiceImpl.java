package com.mqd.gxcj.subjectmanager.service.impl;

import com.mqd.gxcj.subjectmanager.pojo.*;
import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;
import com.mqd.gxcj.subjectmanager.service.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "relevance")
public class UtilsServiceImpl implements UtilsService {
    @Resource
    private RoleService roleService;

    @Resource
    private SubjectService subjectService;

    @Resource
    private DutyService dutyService;

    @Resource
    private RankService rankService;

    @Resource
    private EducationService educationService;

    @Cacheable(key = "'all'")
    @Override
    public RelevanceInfo getRelevanceInfo() {
        List<Subject> subjects = subjectService.list();
        List<Role> roles = roleService.list();
        List<Duty> duties = dutyService.list();
        List<Rank> ranks = rankService.list();
        List<Education> educations = educationService.list();
        RelevanceInfo relevanceInfo = new RelevanceInfo();
        relevanceInfo.setSubjects(subjects)
                .setRoles(roles)
                .setDuties(duties)
                .setRanks(ranks)
                .setEducations(educations);
        return relevanceInfo;
    }

    @Cacheable(key = "'homeStatistic'")
    @Override
    public Map<String, Object> homeStatistic() {
        Map<String, Object> map = new HashMap<>();
        map.put("", "");
        return map;
    }
}
