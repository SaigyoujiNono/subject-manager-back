package com.mqd.gxcj.subjectmanager.service.impl;

import com.mqd.gxcj.subjectmanager.pojo.Account;
import com.mqd.gxcj.subjectmanager.mapper.AccountMapper;
import com.mqd.gxcj.subjectmanager.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mqd.gxcj.subjectmanager.utils.MD5Utils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-18
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public boolean login(Account account) {
        //先从数据库获取到账号
        Account account1 = accountMapper.selectById(account);
        if (account1 == null){
            return false;
        }
        String pwd = MD5Utils.MD5Lower(
                account.getPassword(),
                account.getId());
        //密码比对，成功则返回true
        return account1.getPassword().equals(pwd);
    }
}
