package com.eastng.football.api.service.match;

import java.util.List;
import java.util.Map;

import com.eastng.football.api.exception.FootballWebException;
import com.eastng.football.api.vo.match.LeagueInfoVO;

/**
 * <p>
 * web开头的Service都是为网页提供的service
 * </p>
 * <p>
 * 联赛信息
 * </p>
 * 
 * @author laughing
 * @date 2016-09-22 21:50:09
 * 
 *
 */
public interface WebLeaguesService {

	/**
	 * 查询所有联赛
	 * 
	 * @return
	 */
	public List<LeagueInfoVO> findAllLeagueInfo() throws FootballWebException ;
	
	

	/**
	 * 查询联赛相关信息
	 *
	 * @param leagueNo
	 * @return
	 */
	public Map<String, Object> findLeagueInfo(String leagueNo) throws FootballWebException;
}
