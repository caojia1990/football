package com.eastng.football.api.vo.lottery;

import java.io.Serializable;

import com.eastng.football.api.vo.common.PageParam;

public class QueryOddsParamVO extends PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019371516744593149L;

	/**
	 * 比赛编号
	 */
	private String matchNo;

	/**
	 * @return the matchNo
	 */
	public String getMatchNo() {
		return matchNo;
	}

	/**
	 * @param matchNo the matchNo to set
	 */
	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
	
}
