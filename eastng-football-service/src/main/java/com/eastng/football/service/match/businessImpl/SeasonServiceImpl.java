package com.eastng.football.service.match.businessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.core.entity.match.LeagueSeason;
import com.eastng.football.core.service.match.persistence.LeagueSeasonMapper;
import com.eastng.football.util.BeanUtils;

@Service("seasonService")
public class SeasonServiceImpl implements SeasonService {

    
    @Autowired
    private LeagueSeasonMapper leagueSeasonMapper;
    
    @Override
    public SeasonVo querySeasonBySeasonNo(String seasonNo) {
        LeagueSeason record = this.leagueSeasonMapper.selectBySeasonNo(seasonNo);
        SeasonVo seasonVo = new SeasonVo();
        BeanUtils.copyProperties(record, seasonVo);
        return seasonVo;
    }

}
