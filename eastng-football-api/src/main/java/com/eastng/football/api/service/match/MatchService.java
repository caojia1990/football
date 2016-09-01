package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;


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
	public String saveMatch(MatchVO matchVO) throws FootBallBizException;
	
	/**
	 * 保存赛程信息列表
	 * @param list
	 * @return
	 */
	public Integer saveMatchList(List<MatchVO> list);
	
	/**
	 * 根据条件查询比赛信息
	 * @param paramVO
	 * @return
	 */
	public PageResult<MatchVO> queryMatchSchedule(QueryMatchParamVO paramVO);

	/**
	 * 查询两队交战记录
	 * @param paramVO
	 * @return
	 * @throws FootBallBizException 
	 */
	public PageResult<MatchVO> queryMatchHistory(QueryMatchParamVO paramVO) throws FootBallBizException;
	
	/**
	 * 查询最近的战况
	 * @param paramVO
	 * @return
	 * @throws FootBallBizException 
	 */
	public PageResult<MatchVO> queryRecentMatch(QueryMatchParamVO paramVO) throws FootBallBizException;
	
	/**
	 * 根据比赛编号查询比赛详情
	 * @param matchNo
	 * @return
	 */
	public MatchVO queryMatchByMatchNo(String matchNo);
	
	
	/**
	 * 更新积分榜
	 * @param seasonNo 赛季编号
	 * @throws FootBallBizException 
	 */
	public void updateScoreBoard(String seasonNo, Integer round) throws FootBallBizException;
	
	/**
	 * 查询赛季最新积分榜
	 * @param teamNo
	 * @param SeasonNo
	 * @return 
	 */
	public List<TeamSeasonScoreVO> queryScoreBoard(String SeasonNo);
	
}
