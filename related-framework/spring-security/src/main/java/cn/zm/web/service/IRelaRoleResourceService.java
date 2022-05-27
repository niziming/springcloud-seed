package cn.zm.web.service;

import cn.zm.web.entity.RelaRoleResource;
import cn.zm.web.entity.dto.RelaRoleResourceDTO;
import cn.zm.web.entity.vo.RelaRoleResourceVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IRelaRoleResourceService extends IService<RelaRoleResource> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param RelaRoleResource 角色系统资源关联表入参
    * @return 分页结果
    */
    IPage<RelaRoleResourceVO> selectByPage(IPage<RelaRoleResource> page, RelaRoleResourceDTO RelaRoleResource);
}
