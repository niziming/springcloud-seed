package cn.zm.web.service;

import cn.zm.web.entity.RelaRoleAccount;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.zm.web.entity.dto.RelaRoleAccountDTO;
import cn.zm.web.entity.vo.RelaRoleAccountVO;

public interface IRelaRoleAccountService extends IService<RelaRoleAccount> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param RelaRoleAccount 角色账户关联标表入参
    * @return 分页结果
    */
    IPage<RelaRoleAccountVO> selectByPage(IPage<RelaRoleAccount> page, RelaRoleAccountDTO RelaRoleAccount);
}
