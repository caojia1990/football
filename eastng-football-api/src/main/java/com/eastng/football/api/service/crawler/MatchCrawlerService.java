package com.eastng.football.api.service.crawler;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.crawler.MatchCrawlerParamVO;

public interface MatchCrawlerService {

    /**
     * 爬取指定联赛赛季最新一轮信息
     * @param url
     * @param seasonNo
     * @throws FootBallBizException 
     */
    void crawlerRound(MatchCrawlerParamVO paramVO) throws FootBallBizException;
    
    /**
     * 爬取指定联赛赛季所有信息
     * @param url
     * @param seasonNo
     * @throws FootBallBizException 
     */
    void crawlerSeason(MatchCrawlerParamVO paramVO) throws FootBallBizException;
    
    /**
     * 爬取指定联赛赛季轮次范围内的信息
     * @param paramVO
     * @throws FootBallBizException 
     */
    void crawlerByRoundScope(MatchCrawlerParamVO paramVO) throws FootBallBizException;
}
