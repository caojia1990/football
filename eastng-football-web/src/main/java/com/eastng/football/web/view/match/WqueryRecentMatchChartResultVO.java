package com.eastng.football.web.view.match;

public class WqueryRecentMatchChartResultVO {

	/**
	 * 胜场次
	 */
	private Integer win;
	
	/**
	 * 平场次
	 */
	private Integer draw;
	
	/**
	 * 败场次
	 */
	private Integer lose;

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getLose() {
		return lose;
	}

	public void setLose(Integer lose) {
		this.lose = lose;
	}
	
}
