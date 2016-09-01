package com.eastng.football.service.match.base.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.service.match.base.TeamSeasonScoreBaseService;

@Component("teamSeasonScoreBaseService")
public class TeamSeasonScoreBaseImpl implements TeamSeasonScoreBaseService {

    static Logger logger = Logger.getLogger(TeamSeasonScoreBaseImpl.class);
    
    @Autowired
    private TeamSeasonScoreMapper teamSeasonScoreMapper;
    @Override
    public void saveOrUpdate(TeamSeasonScore teamSeasonScore) throws FootBallBizException {
        
        if(teamSeasonScore == null){
            logger.error("保存积分榜失败，入参为空");
            throw new FootBallBizException("", "保存积分榜信息失败，入参为空");
        }
        
        if(StringUtils.isEmpty(teamSeasonScore.getTeamNo())||StringUtils.isEmpty(teamSeasonScore.getSeasonNo())||teamSeasonScore.getRound()==null){
            logger.error("保存积分榜失败，参数为空");
            throw new FootBallBizException("", "保存积分榜信息失败，参数为空");
        }
        
        TeamSeasonScore record = teamSeasonScoreMapper.selectOneByCondition(teamSeasonScore);
        
        if(record != null){
            teamSeasonScore.setId(record.getId());
            this.teamSeasonScoreMapper.updateByPrimaryKeySelective(teamSeasonScore);
        }else {
            this.teamSeasonScoreMapper.insertSelective(teamSeasonScore);
        }

    }

}
