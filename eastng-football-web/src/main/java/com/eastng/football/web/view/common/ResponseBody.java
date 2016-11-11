package com.eastng.football.web.view.common;

public class ResponseBody<T> extends CommonResponseHead {

	private T responseBody;

	public T getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}
	
}
