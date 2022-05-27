package cn.zm.web.service;

import cn.zm.web.entity.dto.BaseResourceDTO;
import cn.zm.web.entity.vo.BaseResourceVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.zm.web.entity.BaseResource;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IBaseResourceService extends IService<BaseResource> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param BaseResource 系统资源表入参
    * @return 分页结果
    */
    IPage<BaseResourceVO> selectByPage(IPage<BaseResource> page, BaseResourceDTO BaseResource);
}
