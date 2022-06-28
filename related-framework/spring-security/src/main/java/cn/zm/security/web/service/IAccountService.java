package cn.zm.security.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.security.web.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.zm.security.web.entity.dto.AccountDTO;
import cn.zm.security.web.entity.vo.AccountVO;

public interface IAccountService extends IService<Account> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param Account 账户表入参
    * @return 分页结果
    */
    IPage<AccountVO> selectByPage(IPage<Account> page, AccountDTO Account);
}
