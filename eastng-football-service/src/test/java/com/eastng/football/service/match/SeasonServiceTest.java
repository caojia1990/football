package com.eastng.football.service.match;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.test.BaseJunit4Test;

public class SeasonServiceTest extends BaseJunit4Test {
    
    static Logger logger = Logger.getLogger(SeasonServiceTest.class);

    @Resource
    private SeasonService seasonService;
    
    @Test
    public void querySeasonBySeasonNo(){
        
        SeasonVo seasonVo = this.seasonService.querySeasonBySeasonNo("001005001201516");
        
        logger.info(JSON.toJSONString(seasonVo));
        
    }
    
    @Test
    public void queryScoreBoard(){
        
        logger.info(JSON.toJSONString(this.seasonService.queryScoreBoard("001005001201516")));
        
    }
    @Test
    public void querySeasonByLeagueNo(){
        
        List<SeasonVo> list = this.seasonService.querySeasonByLeagueNo("001005001");
        logger.info(JSON.toJSONString(list));
    }
    
    @Test
    public void queryTeamScoreByTeamNoAndSeasonNO() throws FootBallBizException{
        List<TeamSeasonScoreVO> list = this.seasonService.queryTeamScoreByTeamNoAndSeasonNO("001005001201516", "20160820140807468380814");
        logger.info(JSON.toJSONString(list));
    }
}
