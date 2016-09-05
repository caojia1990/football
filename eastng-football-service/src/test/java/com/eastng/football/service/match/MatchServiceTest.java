package com.eastng.football.service.match;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.service.support.ScoreBoardService;
import com.eastng.football.test.BaseJunit4Test;

public class MatchServiceTest extends BaseJunit4Test{
    
    static Logger logger = Logger.getLogger(MatchServiceTest.class);
    
    @Resource
    private ScoreBoardService englandPremierScoreBoard;
    
    @Resource
    private MatchService matchService;
    
    @Resource
    private SeasonService seasonService;
    
    @Test
    @Transactional
    @Rollback(false)
    public void update(){
        try {
            this.englandPremierScoreBoard.update("001005001201516", 7);
        } catch (FootBallBizException e) {
            logger.error("更新积分榜失败", e);
        }
        logger.info("更新完成");
    }
    
    @Test
    @Transactional
    @Rollback(false)
    public void updateScoreBoard(){
        try {
            this.seasonService.updateScoreBoard("001005001201516", 38);
        } catch (FootBallBizException e) {
            logger.error("更新积分榜失败", e);
        }
    }
    
    @Test
    public void queryScoreBoard(){
        List<TeamSeasonScoreVO> list = this.seasonService.queryScoreBoard("001005001201516");
        String jsonString = JSON.toJSONString(list);
        logger.debug(jsonString);
    }

}
