package cn.zm.security.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.zm.security.web.mapper.AccountMapper;
import cn.zm.security.web.service.IAccountService;
import cn.zm.security.web.entity.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.security.web.entity.dto.AccountDTO;
import cn.zm.security.web.entity.vo.AccountVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Override
    public IPage<AccountVO> selectByPage(IPage<Account> page, AccountDTO account) {
        IPage<Account> entityPage = baseMapper.selectPage(page, new QueryWrapper<>(account.convert()));
        IPage<AccountVO> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        IPage<AccountVO> iPage = pageViews.setRecords(entityPage.getRecords().stream().map(entity -> {
            AccountVO vo = AccountVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList()));
        return iPage;
    }
}
