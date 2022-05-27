package cn.zm.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.zm.web.entity.Role;
import cn.zm.web.mapper.RoleMapper;
import cn.zm.web.service.IRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.web.entity.dto.RoleDTO;
import cn.zm.web.entity.vo.RoleVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public IPage<RoleVO> selectByPage(IPage<Role> page, RoleDTO role) {
        IPage<Role> rolePage = baseMapper.selectPage(page, new QueryWrapper<>(role.convert()));

        IPage<RoleVO> pageViews = new Page<>();

        BeanUtil.copyProperties(rolePage, pageViews);

        IPage<RoleVO> iPage = pageViews.setRecords(
          rolePage.getRecords().stream().map(entity -> {
              RoleVO vo = RoleVO.builder().build();
              BeanUtils.copyProperties(entity, vo);
              return vo;
          }).collect(Collectors.toList()));

        return iPage;
    }
}
