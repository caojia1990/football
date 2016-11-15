package com.eastng.football.web.view.common;

import java.util.List;

public class ListResponse<T> extends CommonResponseHead {

	private List<T> responseBody;

	public List<T> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(List<T> responseBody) {
		this.responseBody = responseBody;
	}
	
}
