package com.eastng.football.service.match.bussinessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.entity.match.Match;
import com.eastng.football.service.api.match.MatchService;
import com.eastng.football.service.match.persistence.MatchMapper;
import com.eastng.football.service.vo.MatchVO;

@Service("matchService")
public class MtachServiceImpl implements MatchService {
	
	@Autowired
	private MatchMapper matchMapper;

	public void queryMatchSchedule() {
		// TODO Auto-generated method stub

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