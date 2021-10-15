package cn.zm.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Mr_W
 */

public enum ResultEnum {
	FAIL(1000, "请求错误"),
	CHECK_FAIL(1001, "验证失败"),
	BUSINESS_FAIL(1002, "网络异常"),
	INVALID_PARAMS(1003, "请求参数错误"),
	DATA_EXCEPTION(1004, "数据异常"),
	SUCCESS(2000, "操作成功"),
	SERVER_ERR(5000, "服务异常")
	;

	private int		code;
	private String	msg;
	private Object	data;

	ResultEnum(int code) {
		this.code = code;
	}

	ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}
}
