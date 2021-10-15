package cn.zm.tk.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 功能描述: <br>
 * <分页对象封装>
 *
 * @author 倪子铭
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

    /** 功能描述: <br>
     * <分页构造对象>
     *
     * @param list
     *
     * @author 倪子铭
     * @date 2021/10/15 10:52
     * @return
     */
    public static <T> PageResult build(List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        if (list instanceof Page) BeanUtils.copyProperties(list, pageResult);
        else pageResult.result = list;
        return pageResult;
    }

    public static void main(String[] args) {
        PageResult build = PageResult.build(new ArrayList<>());
        System.out.println(JSONObject.toJSON(build));
    }
}
