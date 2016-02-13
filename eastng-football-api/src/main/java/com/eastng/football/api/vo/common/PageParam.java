package com.eastng.football.api.vo.common;

import java.io.Serializable;

public class PageParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 676540626849589495L;

	private int rows;
	
	private int page;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "CommonParamVO [rows=" + rows + ", page=" + page + "]";
	}
}
