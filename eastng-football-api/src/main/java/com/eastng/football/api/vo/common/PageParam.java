package com.eastng.football.api.vo.common;

public class PageParam {
	
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
