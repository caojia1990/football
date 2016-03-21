package com.eastng.football.web.view.match;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.eastng.football.api.vo.common.PageParam;

/**
 * 查询比赛信息入参VO类
 * @author caojia
 *
 */
public class QueryRecentMatchParamVO extends PageParam {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1612351817989373637L;


	/**
	 * 球队编号
	 */
	private String teamNo;
	
    
    /**
     * 赛事ID
     */
    private String leagueNo;
    
    /**
     * 赛季名称
     */
    private String seasonName;
    
    
    private Date matchTime;
    
    /**
	 * 比赛状态    0：未开始  1：比赛中  2：已结束
	 */
	private String matchStatus;



	/**
	 * @return the teamNo
	 */
	public String getTeamNo() {
		return teamNo;
	}

	/**
	 * @param teamNo the teamNo to set
	 */
	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}

	/**
	 * @return the leagueNo
	 */
	public String getLeagueNo() {
		return leagueNo;
	}

	/**
	 * @param leagueNo the leagueNo to set
	 */
	public void setLeagueNo(String leagueNo) {
		this.leagueNo = leagueNo;
	}

	/**
	 * @return the seasonName
	 */
	public String getSeasonName() {
		return seasonName;
	}

	/**
	 * @param seasonName the seasonName to set
	 */
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}


	/**
	 * @return the matchStatus
	 */
	public String getMatchStatus() {
		return matchStatus;
	}

	/**
	 * @param matchStatus the matchStatus to set
	 */
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	/**
	 * @return the matchTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getMatchTime() {
		return matchTime;
	}

	/**
	 * @param matchTime the matchTime to set
	 */
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryRecentMatchParamVO [teamNo=" + teamNo + ", leagueNo=" + leagueNo + ", seasonName=" + seasonName
				+ ", matchTime=" + matchTime + ", matchStatus=" + matchStatus + "]";
	}

	
}
