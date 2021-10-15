package cn.zm.netflix.tkmybatis.web.mapper;

import cn.zm.netflix.tkmybatis.web.entity.Account;
import cn.zm.tk.base.mapper.BaseMapper;

public interface AccountMapper extends BaseMapper<Account> {
    @Override
    default Class<Account> entityClass () {return Account.class;}
}
