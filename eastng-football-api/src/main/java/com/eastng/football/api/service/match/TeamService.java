package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.vo.match.TeamVO;

public interface TeamService {
	
	public Integer saveTeam(TeamVO paramVO);

	public Integer saveTeams(List<TeamVO> list);
	
	public TeamVO queryTeamByName(String teamName);
}
