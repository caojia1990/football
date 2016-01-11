package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;


/**
 * 赛事服务接口
 * @author caojia
 */
public interface MatchService {
	
	/**
	 * 保存赛程信息
	 * @param matchVO
	 * @return
	 * @throws FootBallBizException 
	 */
	public Integer saveMatch(MatchVO matchVO) throws FootBallBizException;
	
	/**
	 * 根据条件查询比赛信息
	 * @param paramVO
	 * @return
	 */
	public List<MatchVO> queryMatchSchedule(QueryMatchParamVO paramVO);

	/**
	 * 根据比赛编号查询比赛详情
	 * @param matchNo
	 * @return
	 */
	public MatchVO queryMatchByMatchNo(String matchNo);
	
	/**
	 * 查询两队历史交战记录
	 * @param hostTeamId
	 * @param guestTeamId
	 */
	public void queryMatchHistoryByTeams(String hostTeamId,String guestTeamId);
	
	/**
	 * 更新积分榜
	 * @param leagueNo 联赛编号
	 * @param seasonName 赛季名称
	 * @throws FootBallBizException 
	 */
	public void updateScoreBoard(String leagueNo,String seasonName) throws FootBallBizException;
}
