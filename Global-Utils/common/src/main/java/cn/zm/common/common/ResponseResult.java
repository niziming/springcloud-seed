package cn.zm.common.common;

import cn.zm.common.enums.ResultEnum;
import lombok.Data;

/**
 * @author Mr_W
 * @date 2021/2/16 13:48
 * @description 封装返回值
 */
@Data
public class ResponseResult<T> {

    private Integer code;
    private String msg;
    private T data;

    private ResponseResult(){
    }

    private ResponseResult(ResultEnum resultEnum, T data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    private ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult<T> fail(T data){
        return new ResponseResult<>(ResultEnum.FAIL, data);

    }

    public static <T> ResponseResult<T> succ(T data){
        return new ResponseResult<>(ResultEnum.SUCCESS, data);
    }
    public static <T> ResponseResult<T> succ(){
        return new ResponseResult<>(ResultEnum.SUCCESS, null);
    }
}
