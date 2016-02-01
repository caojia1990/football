package com.eastng.football.api.vo.match;

import java.io.Serializable;
import java.util.Date;

/**
 * 比赛详情VO类
 * @author caojia
 */
public class MatchVO implements Serializable {

	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = -5281543521352306212L;
	
	/**
	 * 比赛编号(后台自动生成)
	 */
	private String matchNo;
	
	/**
	 * 主队球队编号
	 */
	private String hostTeamNo;
	
	/**
	 * 主队简称
	 */
	private String hostShortName;
	
	/**
	 * 客队球队编号
	 */
	private String guestTeamNo;
	
	/**
	 * 客队简称
	 */
    private String guestShortName;
    
    /**
     * 赛事编号
     */
    private String leagueNo;
    
    /**
     * 赛季名称
     */
    private String seasonName;
    
    /**
     * 比赛时间
     */
    private Date matchTime;
    
    /**
     * 轮次
     */
    private Integer round;
    
    /**
     * 主队进球
     */
    private Integer hostGoal;
    
    /**
     * 客队进球
     */
	private Integer guestGoal;
	
	/**
	 * 半场主队进球
	 */
	private Integer halfTimeHostGoal;
	
	/**
	 * 半场客队进球
	 */
	private Integer halfTimeGuestGoal;
	
	/**
	 * 比赛状态    0：未开始  1：比赛中  2：已结束
	 */
	private String matchStatus;
    
	/**
	 * 比赛编号(后台自动生成)
	 */
	public String getMatchNo() {
		return matchNo;
	}

	/**
	 * 比赛编号(后台自动生成)
	 */
	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}

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
	 * @return the hostShortName
	 */
	public String getHostShortName() {
		return hostShortName;
	}

	/**
	 * @param hostShortName the hostShortName to set
	 */
	public void setHostShortName(String hostShortName) {
		this.hostShortName = hostShortName;
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
	 * @return the guestShortName
	 */
	public String getGuestShortName() {
		return guestShortName;
	}

	/**
	 * @param guestShortName the guestShortName to set
	 */
	public void setGuestShortName(String guestShortName) {
		this.guestShortName = guestShortName;
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
	 * @return the matchTime
	 */
	public Date getMatchTime() {
		return matchTime;
	}

	/**
	 * @param matchTime the matchTime to set
	 */
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
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
	 * @return the hostGoal
	 */
	public Integer getHostGoal() {
		return hostGoal;
	}

	/**
	 * @param hostGoal the hostGoal to set
	 */
	public void setHostGoal(Integer hostGoal) {
		this.hostGoal = hostGoal;
	}

	/**
	 * @return the guestGoal
	 */
	public Integer getGuestGoal() {
		return guestGoal;
	}

	/**
	 * @param guestGoal the guestGoal to set
	 */
	public void setGuestGoal(Integer guestGoal) {
		this.guestGoal = guestGoal;
	}

	/**
	 * @return the halfTimeHostGoal
	 */
	public Integer getHalfTimeHostGoal() {
		return halfTimeHostGoal;
	}

	/**
	 * @param halfTimeHostGoal the halfTimeHostGoal to set
	 */
	public void setHalfTimeHostGoal(Integer halfTimeHostGoal) {
		this.halfTimeHostGoal = halfTimeHostGoal;
	}

	/**
	 * @return the halfTimeGuestGoal
	 */
	public Integer getHalfTimeGuestGoal() {
		return halfTimeGuestGoal;
	}

	/**
	 * @param halfTimeGuestGoal the halfTimeGuestGoal to set
	 */
	public void setHalfTimeGuestGoal(Integer halfTimeGuestGoal) {
		this.halfTimeGuestGoal = halfTimeGuestGoal;
	}

	/**
	 * 比赛状态    0：未开始  1：比赛中  2：已结束
	 */
	public String getMatchStatus() {
		return matchStatus;
	}

	/**
	 * 比赛状态    0：未开始  1：比赛中  2：已结束
	 */
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatchVO [matchNo=" + matchNo + ", hostTeamNo=" + hostTeamNo + ", hostShortName=" + hostShortName
				+ ", guestTeamNo=" + guestTeamNo + ", guestShortName=" + guestShortName + ", leagueNo=" + leagueNo
				+ ", seasonName=" + seasonName + ", matchTime=" + matchTime + ", round=" + round + ", hostGoal="
				+ hostGoal + ", guestGoal=" + guestGoal + ", halfTimeHostGoal=" + halfTimeHostGoal
				+ ", halfTimeGuestGoal=" + halfTimeGuestGoal + ", matchStatus=" + matchStatus + "]";
	}
	
}
