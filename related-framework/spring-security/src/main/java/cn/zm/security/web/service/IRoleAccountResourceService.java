package cn.zm.security.web.service;

import cn.zm.security.web.entity.dto.RoleAccountResourceDTO;
import cn.zm.security.web.entity.vo.RoleAccountResourceVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.security.web.entity.RoleAccountResource;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IRoleAccountResourceService extends IService<RoleAccountResource> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param RoleAccountResource VIEW入参
    * @return 分页结果
    */
    IPage<RoleAccountResourceVO> selectByPage(IPage<RoleAccountResource> page, RoleAccountResourceDTO RoleAccountResource);
}
