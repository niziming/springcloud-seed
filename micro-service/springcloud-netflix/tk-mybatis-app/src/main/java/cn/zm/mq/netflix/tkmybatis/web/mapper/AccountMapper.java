package cn.zm.mq.netflix.tkmybatis.web.mapper;

import cn.zm.mq.netflix.tkmybatis.web.entity.Account;
import cn.zm.mq.tk.base.mapper.BaseMapper;

public interface AccountMapper extends BaseMapper<Account> {
    @Override
    default Class<Account> entityClass () {return Account.class;}
}
