package com.eastng.football.service.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastng.football.api.service.crawler.SeasonCrawlerService;
import com.eastng.football.service.support.impl.PremierLeagueSeasonServiceImpl;

public class SeasonFactory {
    
    private static final Log log = LogFactory.getLog(SeasonFactory.class);
    
    public SeasonCrawlerService createSeason(String seasonNo){
        log.info("创建赛季爬虫service" + seasonNo);
        
        //return (SeasonCrawlerService) SpringContextUtil.getBean("premierLeagueSeason");
        return new PremierLeagueSeasonServiceImpl();
    }
}
