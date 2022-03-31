package com.mqd.gxcj.subjectmanager.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqd.gxcj.subjectmanager.pojo.UserRole;
import com.mqd.gxcj.subjectmanager.service.RoleService;
import com.mqd.gxcj.subjectmanager.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserRoleService userRoleService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        System.out.println(o);
        log.info("{}-{}",o,s);
        List<String> list = new ArrayList<>();
        list.add("admin-get");
        return list;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return userRoleService.getRoleList((String) o);
    }
}
