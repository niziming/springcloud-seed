package cn.zm.common.base;

import cn.zm.common.enums.ResultEnum;
import lombok.Data;

/**
 * @author Mr_W
 * @date 2021/2/16 13:48
 * @description 封装返回值
 */
@Data
public class ResResult<T> {

    private Integer code;
    private String msg;
    private T data;

    private ResResult(){
    }

    private ResResult(ResultEnum resultEnum, T data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    private ResResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResResult<T> fail(T data){
        return new ResResult<>(ResultEnum.FAIL, data);

    }

    public static <T> ResResult<T> succ(T data){
        return new ResResult<>(ResultEnum.SUCCESS, data);
    }
    public static <T> ResResult<T> succ(){
        return new ResResult<>(ResultEnum.SUCCESS, null);
    }
}
