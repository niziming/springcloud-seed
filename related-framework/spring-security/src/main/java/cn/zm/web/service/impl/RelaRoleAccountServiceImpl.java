package cn.zm.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.zm.web.entity.RelaRoleAccount;
import cn.zm.web.entity.vo.AccountVO;
import cn.zm.web.mapper.RelaRoleAccountMapper;
import cn.zm.web.service.IRelaRoleAccountService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.web.entity.dto.RelaRoleAccountDTO;
import cn.zm.web.entity.vo.RelaRoleAccountVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zm.plus.utils.ConvertUtil;

import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
public class RelaRoleAccountServiceImpl extends ServiceImpl<RelaRoleAccountMapper, RelaRoleAccount> implements IRelaRoleAccountService {
    @Override
    public IPage<RelaRoleAccountVO> selectByPage(IPage<RelaRoleAccount> page, RelaRoleAccountDTO rela_role_account) {
        IPage<RelaRoleAccount> relaRoleAccount = baseMapper.selectPage(page, new QueryWrapper<>(rela_role_account.convert()));
        IPage<RelaRoleAccountVO> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        IPage<RelaRoleAccountVO> iPage = pageViews.setRecords(
          relaRoleAccount.getRecords().stream().map(entity -> {
              RelaRoleAccountVO vo = RelaRoleAccountVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList()));
        return iPage;
    }
}
