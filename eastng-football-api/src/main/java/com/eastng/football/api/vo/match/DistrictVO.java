package com.eastng.football.api.vo.match;

import java.io.Serializable;

public class DistrictVO implements Serializable {

	private static final long serialVersionUID = 6725372028509075670L;

	/** 地区编号 */
	private String districtNo;
	
	/** 地区名称 */
	private String districtName;
	
	/** 地区等级 */
	private String districtLevel;
	
	/** 上级地区编号 */
	private String parentDistrictNo;
	
	/** 拼音首字母 */
	private String firstLetter;

	/**
	 * 地区编号
	 */
	public String getDistrictNo() {
		return districtNo;
	}

	/**
	 * 地区编号
	 */
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	/**
	 * 地区名称
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * 地区名称
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * 地区等级
	 */
	public String getDistrictLevel() {
		return districtLevel;
	}

	/**
	 * 地区等级
	 */
	public void setDistrictLevel(String districtLevel) {
		this.districtLevel = districtLevel;
	}

	/**
	 * 上级地区编号
	 */
	public String getParentDistrictNo() {
		return parentDistrictNo;
	}

	/**
	 * 上级地区编号
	 */
	public void setParentDistrictNo(String parentDistrictNo) {
		this.parentDistrictNo = parentDistrictNo;
	}

	/**
	 * 拼音首字母
	 */
	public String getFirstLetter() {
		return firstLetter;
	}

	/**
	 * 拼音首字母
	 */
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	
	
}
