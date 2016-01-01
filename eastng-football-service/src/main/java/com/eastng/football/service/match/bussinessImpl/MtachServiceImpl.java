package com.eastng.football.service.match.bussinessImpl;

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
public class MtachServiceImpl implements MatchService {
	
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
		System.out.println(list);
		return null;
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
