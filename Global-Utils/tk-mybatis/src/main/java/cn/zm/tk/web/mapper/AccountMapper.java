package cn.zm.tk.web.mapper;

import cn.zm.tk.base.mapper.BaseMapper;
import cn.zm.tk.web.entity.Account;

public interface AccountMapper extends BaseMapper<Account> {
    @Override
    default Class entityClass() {
        return Account.class;
    }
}