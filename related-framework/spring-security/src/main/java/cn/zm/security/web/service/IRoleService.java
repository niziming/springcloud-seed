package cn.zm.security.web.service;

import cn.zm.security.web.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.zm.security.web.entity.dto.RoleDTO;
import cn.zm.security.web.entity.vo.RoleVO;

public interface IRoleService extends IService<Role> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param Role 角色表入参
    * @return 分页结果
    */
    IPage<RoleVO> selectByPage(IPage<Role> page, RoleDTO Role);
}
