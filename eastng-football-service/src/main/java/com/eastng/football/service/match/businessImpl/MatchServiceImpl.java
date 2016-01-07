package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.MatchVO;
import com.eastng.football.api.vo.QueryMatchParamVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;
import com.eastng.football.core.entity.match.TeamLeagueSeason;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamCupSeasonMapper;
import com.eastng.football.core.service.match.persistence.TeamLeagueSeasonMapper;
import com.eastng.football.util.BeanUtils;

@Service("matchService")
public class MatchServiceImpl implements MatchService {
	
	static Logger logger = Logger.getLogger(MatchServiceImpl.class);
	
	@Autowired
	private MatchMapper matchMapper;
	
	@Autowired
	private TeamLeagueSeasonMapper leagueSeasonMapper;
	
	@Autowired
	private TeamCupSeasonMapper teamCupSeasonMapper;
	
	@Autowired
	private LeagueInfoMapper leagueInfoMapper;

	/**
	 * 根据条件查询比赛信息
	 * @param paramVO
	 * @return
	 */
	public List<MatchVO> queryMatchSchedule(QueryMatchParamVO paramVO) {
		
		logger.info("查询比赛信息列表入参："+paramVO.toString());
		
		Map<String,Object> paramMap = BeanUtils.bean2Map(paramVO);
		List<Match> list = this.matchMapper.queryMatchByCondition(paramMap);
		List<MatchVO> resultList = new ArrayList<MatchVO>();
		for(Match match:list){
			MatchVO matchVO = new MatchVO();
			matchVO.setLeagueNo(match.getLeagueNo());
			matchVO.setGuestGoal(match.getGuestGoal());
			matchVO.setGuestShortName(match.getHostShortName());
			matchVO.setGuestTeamNo(match.getHostTeamNo());
			matchVO.setHalfTimeGuestGoal(match.getHalfTimeHostGoal());
			matchVO.setHalfTimeHostGoal(match.getHalfTimeHostGoal());
			matchVO.setHostGoal(match.getHostGoal());
			matchVO.setHostShortName(match.getHostShortName());
			matchVO.setHostTeamNo(match.getHostTeamNo());
			matchVO.setMatchNo(match.getMatchNo());
			matchVO.setMatchStatus(match.getMatchStatus());
			matchVO.setMatchTime(match.getMatchTime());
			matchVO.setRound(match.getRound());
			matchVO.setSeasonName(match.getSeasonName());
			resultList.add(matchVO);
		}
		return resultList;
	}

	public void querySeasonListByEventId(String eventId) {
		// TODO Auto-generated method stub

	}

	public void querySeasonInfoByCondition(String eventId, String seasonName) {
		// TODO Auto-generated method stub

	}

	public void queryMatchHistoryByTeams(String hostTeamId, String guestTeamId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 保存比赛信息
	 * @param matchVO
	 * @return
	 * @throws FootBallBizException 
	 */
	public Integer saveMatch(MatchVO matchVO) throws FootBallBizException{
		
		//比赛状态 0：未开始 1：比赛中 2：已结束
		String matchStatus = matchVO.getMatchStatus();
		if(StringUtils.isEmpty(matchStatus)){
			logger.error("比赛状态不能为空");
			throw new FootBallBizException("", "比赛状态不能为空");
		}
		
		String leagueNo = matchVO.getLeagueNo();
		if(StringUtils.isEmpty(leagueNo)){
			logger.error("赛事编号不能为空");
			throw new FootBallBizException("", "赛事编号不能为空");
		}
		LeagueInfo leagueInfo= this.leagueInfoMapper.selectByLeagueNo(leagueNo);
        if(leagueInfo ==null ){
        	logger.error("赛事编号不存在");
			throw new FootBallBizException("", "赛事编号不存在");
        }
		
		Match match = new Match();
		match.setMatchNo(matchVO.getMatchNo());
		match.setLeagueNo(leagueNo);
		match.setGuestGoal(matchVO.getGuestGoal());
		match.setGuestShortName(matchVO.getGuestShortName());
		match.setGuestTeamNo(matchVO.getGuestTeamNo());
		match.setHalfTimeGuestGoal(matchVO.getHalfTimeGuestGoal());
		match.setHostGoal(match.getHostGoal());
		match.setHostShortName(match.getHostShortName());
		match.setMatchStatus(matchStatus);
		int result = this.matchMapper.saveMatch(match);
		
		return null;
	}
	
	/**
	 * 更新积分榜
	 * @param leagueNo 联赛编号
	 * @param seasonName 赛季名称
	 * @throws FootBallBizException 
	 */
	public void updateScoreBoard(String leagueNo,String seasonName) throws FootBallBizException{
		LeagueInfo leagueInfo = this.leagueInfoMapper.selectByLeagueNo(leagueNo);
		if(leagueInfo == null){
			logger.error("赛事编号不存在");
			throw new FootBallBizException("", "赛事编号不存在");
		}
		if(CommonConstant.LEAGUE_INFO_EVENT_TYPE_LEAGUE.equals(leagueInfo.getEventType())){
			MatchExample example = new MatchExample();
			example.setOrderByClause("ROUND ASC");
			example.createCriteria().andLeagueNoEqualTo(leagueNo)
				.andSeasonNameEqualTo(seasonName);
			List<Match> list = this.matchMapper.selectByExample(example);
			this.matchMapper.deleteByExample(example);
			for(Match match:list){
				//主队积分信息
				TeamLeagueSeason season = new TeamLeagueSeason();
				season.setLeagueNo(leagueNo);
				season.setSeasonName(seasonName);
				season.setTeamNo(match.getHostTeamNo());
				season.setTeamShortName(match.getHostShortName());
				season.setRound(match.getRound());
				
			}
		}
	}
}
