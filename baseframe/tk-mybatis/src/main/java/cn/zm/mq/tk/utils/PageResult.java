package cn.zm.mq.tk.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/** 功能描述: <br>
 * <分页对象封装>
 *
 * @author 十渊
 * @date 2021/10/15 10:53
 * @return
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="分页结果对象")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 3496837282957001918L;
    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("条数")
    private Integer pageSize;

    @ApiModelProperty("结果")
    private List<T> result;


    // public static <T> PageResult build(List<T> list) {
    //     PageResult<T> pageResult = new PageResult<>();
    //     if (list instanceof Page) {
    //         BeanUtils.copyProperties(list, pageResult);
    //     }
    //     else pageResult.result = list;
    //     return pageResult;
    // }

    /** 功能描述: <br>
     * <分页构建>
     *
     * @param list
     *
     * @author 十渊
     * @date 2021/10/15 15:15
     * @return cn.zm.tk.utils.PageResult
     */
    public static PageResult build(List list) {
        PageResult pageResult = new PageResult();
        if (list instanceof Page) BeanUtils.copyProperties(list, pageResult);
        pageResult.result = ConvertUtil.convertList(list);
        return pageResult;
    }
}
