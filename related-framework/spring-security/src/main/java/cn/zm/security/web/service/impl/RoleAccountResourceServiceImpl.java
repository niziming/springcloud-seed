package cn.zm.security.web.service.impl;

import cn.zm.security.web.entity.dto.RoleAccountResourceDTO;
import cn.zm.security.web.entity.vo.RoleAccountResourceVO;
import cn.zm.security.web.service.IRoleAccountResourceService;
import cn.zm.security.web.entity.RoleAccountResource;
import cn.zm.security.web.mapper.RoleAccountResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;


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
