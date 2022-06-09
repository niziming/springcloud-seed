package cn.zm.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.zm.web.entity.RelaRoleResource;
import cn.zm.web.mapper.RelaRoleResourceMapper;
import cn.zm.web.service.IRelaRoleResourceService;
import cn.zm.web.entity.dto.RelaRoleResourceDTO;
import cn.zm.web.entity.vo.RelaRoleResourceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
public class RelaRoleResourceServiceImpl extends ServiceImpl<RelaRoleResourceMapper, RelaRoleResource> implements IRelaRoleResourceService {
    @Override
    public IPage<RelaRoleResourceVO> selectByPage(IPage<RelaRoleResource> page, RelaRoleResourceDTO rela_role_resource) {
        IPage<RelaRoleResource> entityPage = baseMapper.selectPage(page, new QueryWrapper<>(rela_role_resource.convert()));
        IPage<RelaRoleResourceVO> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        IPage<RelaRoleResourceVO> iPage = pageViews.setRecords(entityPage.getRecords().stream().map(entity -> {
            RelaRoleResourceVO vo = RelaRoleResourceVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList()));
        return iPage;
    }
}
