package com.eastng.football.api.exception;

import com.eastng.football.api.constant.StatusConstant;
import com.eastng.football.api.result.Result;

/**
 * 
 * @author laughing
 * @date 2016-10-04 21:46:23
 */
public class FootballException extends Exception implements Result {

	private static final long serialVersionUID = -6805033708157300262L;

	private int code = StatusConstant.Code.error;

	private String additionMessage = "";

	private String message = StatusConstant.Message.error;

	public FootballException() {
		super();
	}

	public FootballException(String message, Throwable cause) {
		super(message, cause);
		// this.message = message;
	}

	public FootballException(String message) {
		super(message);
	}

	public FootballException(ExceptionCode exceptionCode) {
		super(exceptionCode.getMessage());
		this.code = exceptionCode.getCode();
		this.message = exceptionCode.getMessage();
	}

	/**
	 * 
	 * @param exceptionCode
	 * @param additionMessage
	 *            附加信息
	 */
	public FootballException(ExceptionCode exceptionCode, String additionMessage) {
		super(exceptionCode.getMessage());
		this.code = exceptionCode.getCode();
		this.message = exceptionCode.getMessage();
		this.additionMessage = additionMessage;
	}

	// public String getAdditionMessage() {
	// return additionMessage;
	// }

	public int getCode() {
		return this.code;
	}

	/**
	 * 得到异常的信息
	 */
	public String getMessage() {
		if (null != additionMessage && !additionMessage.equals("")) {
			return new StringBuilder().append(this.message).append(additionMessage).toString();
		}
		return this.message;
	}
}
