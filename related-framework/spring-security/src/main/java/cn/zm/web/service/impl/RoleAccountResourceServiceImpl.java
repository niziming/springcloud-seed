package cn.zm.web.service.impl;

import cn.zm.web.entity.RoleAccountResource;
import cn.zm.web.mapper.RoleAccountResourceMapper;
import cn.zm.web.service.IRoleAccountResourceService;
import cn.zm.web.entity.dto.RoleAccountResourceDTO;
import cn.zm.web.entity.vo.RoleAccountResourceVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zm.plus.utils.ConvertUtil;


@Service
@Transactional(rollbackFor = Exception.class)
public class RoleAccountResourceServiceImpl extends ServiceImpl<RoleAccountResourceMapper, RoleAccountResource> implements IRoleAccountResourceService {
    @Override
    public IPage<RoleAccountResourceVO> selectByPage(IPage<RoleAccountResource> page, RoleAccountResourceDTO RoleAccountResource) {
        return null;
    }
    // @Override
    // public IPage<RoleAccountResourceVO> selectByPage(IPage<RoleAccountResource> page, RoleAccountResourceDTO role_account_resource) {
    //     IPage<RoleAccountResource> role_account_resourcePage = baseMapper.selectPage(page, new QueryWrapper<>(role_account_resource.convert()));
    //     return ConvertUtil.buildPage(role_account_resourcePage);
    // }
}
