package com.eastng.football.service.match;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.service.support.ScoreBoardService;
import com.eastng.football.test.BaseJunit4Test;

public class MatchServiceTest extends BaseJunit4Test{
    
    static Logger logger = Logger.getLogger(MatchServiceTest.class);
    
    @Resource
    private ScoreBoardService englandPremierScoreBoard;
    
    @Resource
    private MatchService matchService;
    
    @Test
    @Transactional
    @Rollback(false)
    public void update(){
        this.englandPremierScoreBoard.update("001005001201516", 7);
        logger.info("更新完成");
    }
    
    @Test
    @Transactional
    @Rollback(false)
    public void updateScoreBoard(){
        try {
            this.matchService.updateScoreBoard("001005001201516", 6);
        } catch (FootBallBizException e) {
            logger.error("更新积分榜失败", e);
        }
    }

}
