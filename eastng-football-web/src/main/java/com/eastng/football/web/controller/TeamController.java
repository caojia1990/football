package com.eastng.football.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.web.view.common.ListResponseBody;
import com.eastng.football.web.view.team.WqueryTeamHistoryScoreParamVO;

@Controller
public class TeamController {
    
    @Autowired
    private SeasonService seasonService;

    @RequestMapping(value="queryTeamScoreHistory")
    @ResponseBody
    public ListResponseBody<TeamSeasonScoreVO> queryTeamScoreHistory(WqueryTeamHistoryScoreParamVO paramVO){
        ListResponseBody<TeamSeasonScoreVO> responseBody = new ListResponseBody<TeamSeasonScoreVO>();
        
        List<TeamSeasonScoreVO> list = this.seasonService.queryTeamScoreByTeamNoAndSeasonNO(paramVO.getSeasonNo(), paramVO.getTeamNo());
        
        responseBody.setResponseBody(list);
        return responseBody;
    }
}
