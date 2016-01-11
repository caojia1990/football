package com.eastng.football.api.vo.lottery;

import java.math.BigDecimal;
import java.util.Date;

public class OddsVO {

	/**
	 * 比赛编号
	 */
	private String matchNo;

	/**
	 * 公司名称
	 */
    private String company;


    /**
     * 变赔时间
     */
    private Date changeTime;


    /**
     * 胜
     */
    private BigDecimal win;

    /**
     * 平
     */
    private BigDecimal draw;

    /**
     * 负
     */
    private BigDecimal lose;

    /**
     * 是否初赔
     */
    private String firstOdds;

    /**
	 * 比赛编号
	 */
	public String getMatchNo() {
		return matchNo;
	}

	/**
	 * 比赛编号
	 */
	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}

	/**
	 * 公司名称
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 公司名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
     * 变赔时间
     */
	public Date getChangeTime() {
		return changeTime;
	}

	/**
     * 变赔时间
     */
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	/**
     * 胜
     */
	public BigDecimal getWin() {
		return win;
	}

	/**
     * 胜
     */
	public void setWin(BigDecimal win) {
		this.win = win;
	}

	/**
     * 平
     */
	public BigDecimal getDraw() {
		return draw;
	}

	/**
     * 平
     */
	public void setDraw(BigDecimal draw) {
		this.draw = draw;
	}

	/**
     * 负
     */
	public BigDecimal getLose() {
		return lose;
	}

	/**
     * 负
     */
	public void setLose(BigDecimal lose) {
		this.lose = lose;
	}

	/**
     * 是否初赔
     */
	public String getFirstOdds() {
		return firstOdds;
	}

	/**
     * 是否初赔
     */
	public void setFirstOdds(String firstOdds) {
		this.firstOdds = firstOdds;
	}

    
}
