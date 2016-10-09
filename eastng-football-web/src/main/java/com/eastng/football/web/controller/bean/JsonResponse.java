package com.eastng.football.web.controller.bean;

import com.eastng.football.api.constant.StatusConstant;
import com.eastng.football.api.exception.ExceptionCode;
import com.eastng.football.api.result.XResult;

/**
 * 
 * <p>
 * json 返回的统一 的头信息
 * </p>
 * <p>
 * code = 1 为成功 ；其他为失败 失败原因可以使查看message 的信息
 * </p>
 * 
 * @author laughing
 * @date 2016-09-25 22:17:39
 * @return {"code": 1,"message" : "ok","responseBody":"....."}
 */
@SuppressWarnings("unchecked")
public class JsonResponse<T> extends XResult {

	/**
	 * code = 1 为success 其他都为失败
	 */
	private int code = ExceptionCode.SUCCESS.getCode();
	/**
	 * 返回的信息；当报错时返回相关信息
	 */
	private String message = ExceptionCode.SUCCESS.getMessage();
	/**
	 * 返回信息的主体 ，当status = 1 时
	 */

	private T responseBody = (T) new Object();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}

	public void success(T responseBody) {
		this.code = StatusConstant.Code.success;
		this.message = StatusConstant.Message.success;
		this.responseBody = responseBody;
	}

	public void error() {
		this.code = StatusConstant.Code.error;
		this.message = StatusConstant.Message.error;
		this.responseBody = (T) new Object();
	}

	public void error(String message) {
		this.code = StatusConstant.Code.error;
		this.message = message;
		this.responseBody = (T) new Object();
	}

	@Override
	public String toString() {
		return "JsonResponse [code=" + code + ", message=" + message + ", responseBody=" + responseBody + "]";
	}

}
