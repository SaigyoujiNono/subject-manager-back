package com.mqd.gxcj.subjectmanager.service;

import com.mqd.gxcj.subjectmanager.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
public interface AccountService extends IService<Account> {

    /**
     * 登录方法，密码比对成功则返回true
     * @param account   账号
     */
    boolean login(Account account);
}
