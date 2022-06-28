package cn.zm.mq.netflix.tkmybatis.web.service.impl;

import cn.zm.mq.netflix.tkmybatis.web.service.IAccountService;
import cn.zm.mq.netflix.tkmybatis.web.entity.Account;
import cn.zm.mq.tk.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends BaseServiceImpl<Account> implements IAccountService {
}
