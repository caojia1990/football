package com.eastng.football.service.support;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.eastng.football.service.support.crawler.CrawlerService;
import com.eastng.football.util.SpringContextUtil;

@Component("seasonFactory")
public class SeasonFactory {
    
    private static final Logger log = Logger.getLogger(SeasonFactory.class);
    
    public CrawlerService createSeason(String seasonNo){
        log.info("创建赛季爬虫service" + seasonNo);
        
        return (CrawlerService) SpringContextUtil.getBean("premierLeagueSeasonSerice");
    }
}
