package com.eastng.football.service.crawler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.crawler.MatchCrawlerService;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.crawler.MatchCrawlerParamVO;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.service.support.LeagueFactory;
import com.eastng.football.service.support.SeasonFactory;

public class MatchCrawlerServiceImpl implements MatchCrawlerService{

    private static Logger logger = Logger.getLogger(MatchCrawlerServiceImpl.class);
    
    @Autowired
    private LeagueFactory leagueFactory;
    
    @Autowired
    private SeasonFactory seasonFactory;
    
    @Autowired
    private MatchMapper matchMapper;
    
    @Autowired
    private SeasonService seasonService;

    @Override
    public void crawlerRound(MatchCrawlerParamVO paramVO) throws FootBallBizException {
        if(StringUtils.isEmpty(paramVO)||StringUtils.isEmpty(paramVO.getUrl())
                ||StringUtils.isEmpty(paramVO.getSeasonNo())){
            logger.warn("参数为空");
            throw new FootBallBizException("", "参数为空");
        }
        
        Integer round = this.matchMapper.selectMinRoundBySeasonNo(paramVO.getSeasonNo());
        if(round == null){//如果没有该赛季信息，爬整个赛季
            leagueFactory.createLeague(paramVO.getSeasonNo()).crawler(paramVO);
            return;
        }
        paramVO.setUrl(paramVO.getUrl()+round+ "/");
        try {
            this.seasonFactory.createSeason(paramVO.getSeasonNo()).crawler(paramVO);
            //更新积分榜
            this.seasonService.updateScoreBoard(paramVO.getSeasonNo(), round);
        } catch (FootBallBizException e) {
            logger.error("获取失败，URL： "+ paramVO.getUrl(),e);
            throw new FootBallBizException("", e.getMessage());
        }
        
    }

    @Override
    public void crawlerSeason(MatchCrawlerParamVO paramVO) throws FootBallBizException {
        leagueFactory.createLeague(paramVO.getSeasonNo()).crawler(paramVO);
        
    }

    @Override
    public void crawlerByRoundScope(MatchCrawlerParamVO paramVO) throws FootBallBizException {
        if(paramVO == null||StringUtils.isEmpty(paramVO.getUrl())||StringUtils.isEmpty(paramVO.getSeasonNo())
                ||StringUtils.isEmpty(paramVO.getBeginRound())||StringUtils.isEmpty(paramVO.getEndRound())){
            logger.warn("参数为空");
            throw new FootBallBizException("", "参数为空");
        }
        leagueFactory.createLeague(paramVO.getSeasonNo()).crawler(paramVO);
    }

}
