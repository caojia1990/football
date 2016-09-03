package com.eastng.football.service.support.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.core.entity.match.Team;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamMapper;
import com.eastng.football.service.match.base.TeamSeasonScoreBaseService;
import com.eastng.football.service.support.ScoreBoardService;

@Service("englandPremierScoreBoard")
public class EnglandPremierScoreBoardServiceImpl implements ScoreBoardService {

    static Logger logger = Logger.getLogger(EnglandPremierScoreBoardServiceImpl.class);
    
    @Autowired
    private MatchMapper matchMapper;
    
    @Autowired
    private TeamMapper teamMapper;
    
    @Autowired
    private TeamSeasonScoreBaseService teamSeasonScoreBaseService;
    
    @Override
    public void update(String seasonNo, Integer round) throws FootBallBizException {
        //查询本赛季所有球队
        List<Team> teams = this.teamMapper.selectBySeasonNo(seasonNo);
        
        if(teams == null){
            return ;
        }
        for(Team team: teams){
            logger.info("球队信息：" + ToStringBuilder.reflectionToString(team, ToStringStyle.MULTI_LINE_STYLE));
            
            TeamSeasonScore score = new TeamSeasonScore();
            score.setTeamNo(team.getTeamNo());
            score.setTeamShortName(team.getShortName());
            score.setLeagueNo(seasonNo.substring(0, 9));
            score.setSeasonNo(seasonNo);
            score.setRound(round);
            score.setSeasonName(null);
            
            //查询球队赛季到该轮的主场比赛场次
            Map<String, Object> hostMap = matchMapper.selectCountByHostTeamAndRound(seasonNo, team.getTeamNo(), round);
            //主场已赛
            score.setHostHaveMatch(((Long)hostMap.get("hostPlay")).intValue());
            //主场进球
            score.setHostGoals(((BigDecimal)hostMap.get("hostGoals")).intValue());
            //主场失球
            score.setHostFumble(((BigDecimal)hostMap.get("guestGoals")).intValue());
            //查询球队客场比赛场次
            Map<String, Object> guestMap = matchMapper.selectCountByGuestTeamAndRound(seasonNo, team.getTeamNo(), round);
            //客场已赛
            score.setGuestHaveMatch(((Long)guestMap.get("guestPlay")).intValue());
            //客场进球
            score.setGuestGoals(((BigDecimal)guestMap.get("guestGoals")).intValue());
            //客场失球
            score.setGuestFumble(((BigDecimal)guestMap.get("hostGoals")).intValue());
            
            //总进球数
            score.setGoals(score.getHostGoals()+score.getGuestGoals());
            //总失球数
            score.setFumble(score.getHostFumble()+score.getGuestFumble());
            
            //查询主场胜平负累计
            List<Map<String, Object>> hostResult = this.matchMapper.selectHostResult(seasonNo, team.getTeamNo(), round);
            if(hostResult != null){
                for(Map<String, Object> map: hostResult){
                    if(map.get("result").equals("3")){
                        score.setHostWin(((Long)map.get("sum")).intValue());
                    }else if (map.get("result").equals("1")) {
                        score.setHostDraw(((Long)map.get("sum")).intValue());
                    }else if (map.get("result").equals("0")) {
                        score.setHostLose(((Long)map.get("sum")).intValue());
                    }
                }
            }
            score.setHostWin(score.getHostWin()==null?0:score.getHostWin());
            score.setHostDraw(score.getHostDraw()==null?0:score.getHostDraw());
            score.setHostLose(score.getHostLose()==null?0:score.getHostLose());
            
            
          //查询客场胜平负累计
            List<Map<String, Object>> guestResult = this.matchMapper.selectGuestResult(seasonNo, team.getTeamNo(), round);
            if(guestResult != null){
                for(Map<String, Object> map: guestResult){
                    if(map.get("result").equals("3")){
                        score.setGuestLose(((Long)map.get("sum")).intValue());
                    }else if (map.get("result").equals("1")) {
                        score.setGuestDraw(((Long)map.get("sum")).intValue());
                    }else if (map.get("result").equals("0")) {
                        score.setGuestWin(((Long)map.get("sum")).intValue());
                    }
                }
            }
            score.setGuestLose(score.getGuestLose()==null?0:score.getGuestLose());
            score.setGuestDraw(score.getGuestDraw()==null?0:score.getGuestDraw());
            score.setGuestWin(score.getGuestWin()==null?0:score.getGuestWin());
            
            //胜利场次
            score.setWin(score.getHostWin()+score.getGuestWin());
            //平局场次
            score.setDraw(score.getHostDraw()+score.getGuestDraw());
            //战败场次
            score.setLose(score.getHostLose()+score.getGuestLose());
            
            //主场积分
            score.setHostPoints(score.getHostWin()*3 + score.getHostDraw());
            //客场积分
            score.setGuestPoints(score.getGuestWin()*3 + score.getGuestDraw());
            //积分
            score.setPoints(score.getHostPoints() + score.getGuestPoints());
            
            //保存积分信息
            this.teamSeasonScoreBaseService.saveOrUpdate(score);
        }
    }

}
