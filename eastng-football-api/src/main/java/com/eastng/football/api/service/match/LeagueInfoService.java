package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.vo.match.LeagueInfoVO;

/**
 * 赛事类型服务接口
 * @author caojia
 */
public interface LeagueInfoService {
	
	/**
	 * 保存联赛信息
	 * @param paramVO 联赛信息VO
	 * @return
	 */
	public Integer saveLeagueInfo(LeagueInfoVO paramVO);
	
	/**
	 * 删除所有数据
	 */
	public Integer deleteAll();
	
	/**
	 * 根据条件查询联赛信息
	 * @param leagueInfoVO
	 * @return
	 */
	public List<LeagueInfoVO> queryLeagueInfoByCondition(LeagueInfoVO leagueInfoVO); 
	
	/**
	 * 根据联赛编号查询联赛信息
	 * @param leagueNo
	 * @return
	 */
	LeagueInfoVO queryLeagueInfoByLeagueNo(String leagueNo);
	
}
