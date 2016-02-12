package com.eastng.football.api.vo.common;

import java.util.List;

public class PageResult<T> {
	private Long total;
	
	private List<T> result;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
}
