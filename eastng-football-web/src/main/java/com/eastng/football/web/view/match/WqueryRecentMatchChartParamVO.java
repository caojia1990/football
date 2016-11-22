package com.eastng.football.web.view.match;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//app查询球队赛季近况pieChart图
public class WqueryRecentMatchChartParamVO {
	
	private String teamNo;
	
	private String seasonNo;
	
	private Date matchDate;

	public String getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}

	public String getSeasonNo() {
		return seasonNo;
	}

	public void setSeasonNo(String seasonNo) {
		this.seasonNo = seasonNo;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	

}
