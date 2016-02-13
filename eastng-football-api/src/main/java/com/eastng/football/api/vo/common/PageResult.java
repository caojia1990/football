package com.eastng.football.api.vo.common;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4733641131170708339L;

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
