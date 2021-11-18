package cn.zm.netflix.mybatisplus.web.service.impl;

import cn.zm.netflix.mybatisplus.web.entity.Account;
import cn.zm.netflix.mybatisplus.web.entity.dto.AccountDTO;
import cn.zm.netflix.mybatisplus.web.entity.vo.AccountVO;
import cn.zm.netflix.mybatisplus.web.mapper.AccountMapper;
import cn.zm.netflix.mybatisplus.web.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zm.plus.utils.ConvertUtil;

/** 功能描述: <br>
 * <测->
 *
 * @author 十渊
 * @date 2021/11/17 16:35
 * @return
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Override
    public IPage<AccountVO> selectByPage(IPage<Account> page, AccountDTO account) {
        IPage<Account> accountPage = baseMapper.selectPage(page, new QueryWrapper<>(account.convert()));
        return ConvertUtil.buildPage(accountPage);
    }
}
