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
@Api(tags = "成果管理接口")
@RestController
@RequestMapping("/achievement")
@SaCheckLogin
public class AchievementController {

    @ApiOperation(value = "根据条件查询成果")
    @GetMapping("/query")
    public R queryAchieve() {
        return R.ok();
    }

    @ApiOperation(value = "给项目添加成果")
    @PostMapping("/addFromProject")
    public R addFromProject() {
        return R.ok();
    }

    @ApiOperation(value = "审核成果接口")
    @PostMapping("/checkAchieve")
    public R checkAchieve() {
        return R.ok();
    }

    @ApiOperation(value = "根据id修改成果，只有审核未通过的成果才可以修改")
    @PutMapping("/update")
    public R updateAchieve() {
        return R.ok();
    }

    @ApiOperation(value = "根据id删除成果")
    @DeleteMapping("/delete")
    public R delAchieve() {
        return R.ok();
    }

}
