package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.MatchVO;
import com.eastng.football.api.vo.QueryMatchParamVO;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.util.BeanUtils;

@Service("matchService")
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private MatchMapper matchMapper;

	/**
	 * 根据条件查询比赛信息
	 * @param paramVO
	 * @return
	 */
	public List<MatchVO> queryMatchSchedule(QueryMatchParamVO paramVO) {
		Map<String,Object> paramMap = BeanUtils.bean2Map(paramVO);
		List<Match> list = this.matchMapper.queryMatchByCondition(paramMap);
		List<MatchVO> resultList = new ArrayList<MatchVO>();
		for(Match match:list){
			MatchVO matchVO = new MatchVO();
			matchVO.setEventId(match.getEventId());
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
	 */
	public Integer saveMatch(MatchVO matchVO){
		Match match = new Match();
		match.setMatchNo(matchVO.getMatchNo());
		match.setEventId(matchVO.getEventId());
		match.setGuestGoal(matchVO.getGuestGoal());
		match.setGuestShortName(matchVO.getGuestShortName());
		match.setGuestTeamNo(matchVO.getGuestTeamNo());
		match.setHalfTimeGuestGoal(matchVO.getHalfTimeGuestGoal());
		match.setHostGoal(match.getHostGoal());
		match.setHostShortName(match.getHostShortName());
		return this.matchMapper.saveMatch(match);
	}
}
