package com.eastng.football.service.exception;

public class FootBallBizException extends Exception {

	private static final long serialVersionUID = 5127459206447299438L;
	
	protected String errorCode ;
	
	/**
	 * 无参的构造方法
	 */
	public FootBallBizException(){
		super();
	}
	
	/**
	 * 构造方法
	 * @param msg
	 */
	public FootBallBizException(String errorCode,String msg){
		super(msg);
		this.errorCode = errorCode;
	}
	
}
