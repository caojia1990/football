package com.eastng.football.api.exception;

import com.eastng.football.api.constant.StatusConstant;
import com.eastng.football.api.result.Result;

/**
 * 
 * @author laughing
 * @date 2016-09-28 21:23:03
 * @description exception 的描述
 */
public enum ExceptionCode implements Result {

	SUCCESS(StatusConstant.Code.success, StatusConstant.Message.success),
	// NETWORK_ERROR(-1000, "null point"),
	// 缺少参数 miss
	MISS_PARAM(StatusConstant.Code.missParam, StatusConstant.Message.missParam),
	// 系统错误，未捕捉的错误
	SYSTEM_ERROR(StatusConstant.Code.error, StatusConstant.Message.error),
	// 缺少联赛赛季信息
	MISS_SEASON(StatusConstant.Code.missSeason, StatusConstant.Message.missSeason),
	// 缺少赛季成绩信息
	MISS_TEAMSEASONSCORE(StatusConstant.Code.missTeamSeasonScore, StatusConstant.Message.missTeamSeasonScore),
	// 缺少联赛信息
	MISS_LEAGUES(StatusConstant.Code.missLeagues, StatusConstant.Message.missLeagues);
	/**
	 * 
	 */
	private int code;
	/**
	 * 
	 */
	private String message;

	private ExceptionCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
