package com.eastng.football.service.match.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.service.match.base.ScoreBoardService;

@Service("englandPremierScoreBoard")
public class EnglandPremierScoreBoardServiceImpl implements ScoreBoardService {

    @Autowired
    private MatchMapper matchMapper;
    
    @Override
    public void update(String seasonNo, Integer round) {
        //查询本赛季本轮及以前的所有比赛
        
    }

}
