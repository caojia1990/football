package com.eastng.football.api.vo;

import java.io.Serializable;

public class LeagueInfoVO implements Serializable {

	private static final long serialVersionUID = 3734411669321647779L;

	/**
	 * 联/杯赛编号
	 */
	private String leagueNo;

	/**
	 * 联/杯赛名称
	 */
    private String leagueName;

    /**
     * 联/杯赛简称
     */
    private String leagueShortName;

    /**
     * 赛事类型  0：联赛 1：杯赛
     */
    private String eventType;

    /**
     * 所属洲 
     */
    private String continent;
    
    /**
     * 所属国家
     */
    private String country;
    
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
	 * @return the leagueName
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * @param leagueName the leagueName to set
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * @return the leagueShortName
	 */
	public String getLeagueShortName() {
		return leagueShortName;
	}

	/**
	 * @param leagueShortName the leagueShortName to set
	 */
	public void setLeagueShortName(String leagueShortName) {
		this.leagueShortName = leagueShortName;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
