package com.eastng.football.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.web.view.common.ListResponseBody;

@Controller
public class TeamController {

	@RequestMapping(value="queryTeamScoreHistory")
	@ResponseBody
	public ListResponseBody<TeamSeasonScoreVO> queryTeamScoreHistory(){
		ListResponseBody<TeamSeasonScoreVO> responseBody = new ListResponseBody<TeamSeasonScoreVO>();
		
		
		return responseBody;
	}
}
