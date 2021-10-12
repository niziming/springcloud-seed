package cn.zm.netflix.app.web.service;

import cn.zm.netflix.app.web.entity.dto.AccountDTO;
import cn.zm.netflix.app.web.entity.vo.AccountVO;
import cn.zm.netflix.app.web.entity.Account;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IAccountService extends IService<Account> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param Account 入参
    * @return 分页结果
    */
    IPage<AccountVO> selectByPage(IPage<Account> page, AccountDTO Account);
}
