package com.eastng.football.web.view.match;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.eastng.football.api.vo.common.CommonParamVO;
import com.eastng.football.api.vo.common.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 查询比赛信息入参VO类
 * @author caojia
 *
 */
public class QueryMatchHistoryParamVO extends PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 757490234750583627L;

	
	/**
	 * 主队球队编号
	 */
	private String hostTeamNo;
	
	
	/**
	 * 客队球队编号
	 */
	private String guestTeamNo;
	
    
    /**
     * 赛事ID
     */
    private String leagueNo;
    
    /**
     * 赛季名称
     */
    private String seasonName;
    
    /**
     * 开始时间
     */
    private Date beginDate;
    
    /**
     * 结束时间
     */
    private Date endDate;
    
    /**
     * 轮次
     */
    private Integer round;
	
    /**
	 * 比赛状态    0：未开始  1：比赛中  2：已结束
	 */
	private String matchStatus;


	/**
	 * @return the hostTeamNo
	 */
	public String getHostTeamNo() {
		return hostTeamNo;
	}

	/**
	 * @param hostTeamNo the hostTeamNo to set
	 */
	public void setHostTeamNo(String hostTeamNo) {
		this.hostTeamNo = hostTeamNo;
	}


	/**
	 * @return the guestTeamNo
	 */
	public String getGuestTeamNo() {
		return guestTeamNo;
	}

	/**
	 * @param guestTeamNo the guestTeamNo to set
	 */
	public void setGuestTeamNo(String guestTeamNo) {
		this.guestTeamNo = guestTeamNo;
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
	 * @return the round
	 */
	public Integer getRound() {
		return round;
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(Integer round) {
		this.round = round;
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
	 * @return the beginDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryMatchHistoryParamVO [hostTeamNo=" + hostTeamNo + ", guestTeamNo=" + guestTeamNo + ", leagueNo="
				+ leagueNo + ", seasonName=" + seasonName + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", round=" + round + ", matchStatus=" + matchStatus + "]";
	}
	
}
