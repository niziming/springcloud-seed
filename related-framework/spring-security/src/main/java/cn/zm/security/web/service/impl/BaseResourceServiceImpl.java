package cn.zm.security.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.zm.security.web.entity.BaseResource;
import cn.zm.security.web.mapper.BaseResourceMapper;
import cn.zm.security.web.service.IBaseResourceService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.security.web.entity.dto.BaseResourceDTO;
import cn.zm.security.web.entity.vo.BaseResourceVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
public class BaseResourceServiceImpl extends ServiceImpl<BaseResourceMapper, BaseResource> implements IBaseResourceService {
    @Override
    public IPage<BaseResourceVO> selectByPage(IPage<BaseResource> page, BaseResourceDTO base_resource) {
        IPage<BaseResource> entityPage = baseMapper.selectPage(page, new QueryWrapper<>(base_resource.convert()));
        IPage<BaseResourceVO> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        IPage<BaseResourceVO> iPage = pageViews.setRecords(entityPage.getRecords().stream().map(entity -> {
            BaseResourceVO vo = BaseResourceVO.builder().build();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList()));
        return iPage;
    }
}
