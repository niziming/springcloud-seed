package cn.zm.netflix.tkmybatis.web.service.impl;

import cn.zm.netflix.tkmybatis.web.service.IAccountService;
import cn.zm.netflix.tkmybatis.web.entity.Account;
import cn.zm.tk.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends BaseServiceImpl<Account> implements IAccountService {
}
