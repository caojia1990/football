package com.eastng.football.api.exception;

/**
 * 
 * @author laughing
 * @date 2016-10-04 15:54:47
 *
 */
public class FootballWebException extends FootballException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3866460468682282072L;

	public FootballWebException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FootballWebException(ExceptionCode exceptionCode, String additionMessage) {
		super(exceptionCode, additionMessage);
		// TODO Auto-generated constructor stub
	}

	public FootballWebException(ExceptionCode exceptionCode) {
		super(exceptionCode);
		// TODO Auto-generated constructor stub
	}

	public FootballWebException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FootballWebException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
