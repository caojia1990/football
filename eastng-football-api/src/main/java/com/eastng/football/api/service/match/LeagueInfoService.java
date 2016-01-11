package com.eastng.football.api.service.match;

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
	
}
