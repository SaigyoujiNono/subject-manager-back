package com.mqd.gxcj.subjectmanager.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 专家意见，因为一个项目存在多个专家评审，所以专家意见单独一张表 前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "项目专家管理")
@RestController
@RequestMapping("/projectExpertize")
public class ProjectExpertizeController {

}
