package com.eastng.football.service.api.match;

import com.eastng.football.service.vo.MatchVO;

/**
 * 赛事服务接口
 * @author caojia
 */
public interface MatchService {
	
	/**
	 * 保存比赛信息
	 * @param matchVO
	 * @return
	 */
	public Integer saveMatch(MatchVO matchVO);
	/**
	 * 根据条件查询赛程信息
	 */
	public void queryMatchSchedule();

	/**
	 * 根据赛事种类ID查询联赛或杯赛信息
	 * @param eventId
	 */
	public void querySeasonListByEventId(String eventId);
	
	/**
	 * 根据条件查询赛季详情
	 * @param eventId
	 * @param seasonName
	 */
	public void querySeasonInfoByCondition(String eventId,String seasonName);
	
	/**
	 * 查询两队历史交战记录
	 * @param hostTeamId
	 * @param guestTeamId
	 */
	public void queryMatchHistoryByTeams(String hostTeamId,String guestTeamId);
}
