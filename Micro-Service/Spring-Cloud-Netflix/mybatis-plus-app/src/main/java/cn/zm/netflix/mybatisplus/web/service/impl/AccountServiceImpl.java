package cn.zm.netflix.mybatisplus.web.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.zm.netflix.mybatisplus.web.entity.Account;
import cn.zm.netflix.mybatisplus.web.entity.dto.AccountDTO;
import cn.zm.netflix.mybatisplus.web.entity.vo.AccountVO;
import cn.zm.netflix.mybatisplus.web.mapper.AccountMapper;
import cn.zm.netflix.mybatisplus.web.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zm.plus.utils.ConvertUtil;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Override
    public IPage<AccountVO> selectByPage(IPage<Account> page, AccountDTO account) {
        IPage<Account> accountPage = baseMapper.selectPage(page, new QueryWrapper<>(account.convert()));
        return ConvertUtil.buildPage(accountPage);
    }
}
