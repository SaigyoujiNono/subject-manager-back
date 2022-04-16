package com.mqd.gxcj.subjectmanager.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mqd.gxcj.subjectmanager.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Api(tags = "公告接口")
@RestController
@RequestMapping("/notice")
@SaCheckLogin
public class NoticeController {

    @ApiOperation(value = "获取首页公告")
    @GetMapping("/getHome")
    public R homeNotice() {
        return R.ok();
    }

    @ApiOperation(value = "根据条件查询公告")
    @GetMapping("/query")
    public R getNoticeByQuery() {
        return R.ok();
    }

    @ApiOperation(value = "根据id获取公告信息")
    @GetMapping("/get")
    public R getNoticeById(Integer id) {
        return R.ok();
    }

    @ApiOperation(value = "发布公告")
    @PostMapping("/publish")
    public R publishNotice() {
        return R.ok();
    }

    @ApiOperation(value = "更新公告")
    @PutMapping("/update")
    public R updateNotice() {
        return R.ok();
    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("/delete")
    public R delNotice() {
        return R.ok();
    }
}
