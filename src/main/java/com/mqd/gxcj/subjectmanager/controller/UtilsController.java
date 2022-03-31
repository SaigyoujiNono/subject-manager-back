package com.mqd.gxcj.subjectmanager.controller;

import com.mqd.gxcj.subjectmanager.pojo.dto.RelevanceInfo;
import com.mqd.gxcj.subjectmanager.service.UtilsService;
import com.mqd.gxcj.subjectmanager.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用于返回一些不经常改变的信息，工具性质的类
 */
@Api(tags = "工具接口")
@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Resource
    private UtilsService utilsService;

    @ApiOperation(value = "返回学科表、角色表、学历表、职务表、职称表")
    @GetMapping("/baseInfo")
    public R getAllInfo(){
        RelevanceInfo relevanceInfo = utilsService.getRelevanceInfo();
        return R.ok().put("allInfo",relevanceInfo);
    }
}
