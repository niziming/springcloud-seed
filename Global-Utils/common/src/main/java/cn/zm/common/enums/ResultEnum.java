package cn.zm.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Mr_W
 */

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {
	/**
	 * 验证失败
	 */
	FAIL(1000, "请求错误"),
	/**
	 * 验证失败
	 */
	CHECK_FAIL(1001, "验证失败"),
	/**
	 * 网络异常
	 */
	BUSINESS_FAIL(1002, "网络异常"),
	/**
	 * 请求参数错误
	 */
	INVALID_PARAMS(1003, "请求参数错误"),
	/**
	 * 数据异常
	 */
	DATA_EXCEPTION(1004, "数据异常"),
	/**
	 *
	 */
	SUCCESS(2000, "操作成功");

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
