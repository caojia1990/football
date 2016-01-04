package com.eastng.football.api.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 球队信息VO
 * @author caojia
 */
public class TeamVO implements Serializable {

	private static final long serialVersionUID = 6742177302486687294L;

	/** 球队编号 */
	private String teamNo;

	/** 球队全称 */
    private String teamName;

    /** 球队简称 */
    private String shortName;

    /** 球队英文名 */
    private String teamNameEng;
    
    /**球队类型  0：国家队；1：俱乐部 */
    private String teamType;

    /**所属洲 */
    private String continent;

    /**所属国家 */
    private String country;

    /**球队建立时间 */
    private Date establishDate;

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
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the teamNameEng
	 */
	public String getTeamNameEng() {
		return teamNameEng;
	}

	/**
	 * @param teamNameEng the teamNameEng to set
	 */
	public void setTeamNameEng(String teamNameEng) {
		this.teamNameEng = teamNameEng;
	}

	/**
	 * @return the teamType
	 */
	public String getTeamType() {
		return teamType;
	}

	/**
	 * @param teamType the teamType to set
	 */
	public void setTeamType(String teamType) {
		this.teamType = teamType;
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

	/**
	 * @return the establishDate
	 */
	public Date getEstablishDate() {
		return establishDate;
	}

	/**
	 * @param establishDate the establishDate to set
	 */
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
    
}
