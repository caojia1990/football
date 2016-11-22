package com.eastng.football.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.web.view.common.ListResponse;
import com.eastng.football.web.view.team.WqueryTeamHistoryScoreParamVO;

@Controller
public class TeamController {
    
    @Autowired
    private SeasonService seasonService;

    @RequestMapping(value="queryTeamScoreHistory")
    @ResponseBody
    public ListResponse<TeamSeasonScoreVO> queryTeamScoreHistory(WqueryTeamHistoryScoreParamVO paramVO){
        ListResponse<TeamSeasonScoreVO> response = new ListResponse<TeamSeasonScoreVO>();
        
        List<TeamSeasonScoreVO> list;
		try {
			list = this.seasonService.queryTeamScoreByTeamNoAndSeasonNO(paramVO.getSeasonNo(), paramVO.getTeamNo());
			response.setResponseBody(list);
		} catch (FootBallBizException e) {
			response.setResponseCode(e.getErrorCode());
			response.setErrorMessage(e.getMessage());
		}
        
        return response;
    }
}
