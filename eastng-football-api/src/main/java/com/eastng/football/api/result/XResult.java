package com.eastng.football.api.result;

/**
 * 
 * @author huang.xiaolong
 * @date 2016-10-04 20:34:36
 */
public class XResult implements Result {

	/**
	 * 
	 */
	private int code;
	private String message;

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
