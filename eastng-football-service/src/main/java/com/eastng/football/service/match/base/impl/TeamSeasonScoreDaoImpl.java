package com.eastng.football.service.match.base.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.service.match.base.TeamSeasonScoreDao;

@Component("teamSeasonScoreDao")
public class TeamSeasonScoreDaoImpl implements TeamSeasonScoreDao {

    static Logger logger = Logger.getLogger(TeamSeasonScoreDaoImpl.class);
    
    @Autowired
    private TeamSeasonScoreMapper teamSeasonScoreMapper;
    @Override
    public void saveOrUpdate(TeamSeasonScore teamSeasonScore) {
        
        TeamSeasonScore record = teamSeasonScoreMapper.selectOneByCondition(teamSeasonScore);
        
        if(record != null){
            teamSeasonScore.setId(record.getId());
            this.teamSeasonScoreMapper.updateByPrimaryKeySelective(teamSeasonScore);
        }else {
            this.teamSeasonScoreMapper.insertSelective(teamSeasonScore);
        }

    }

}
