package com.eastng.football.service.support.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.eastng.football.api.service.crawler.LeagueCrawlerService;
import com.eastng.football.service.support.SeasonFactory;

public class ChampionshipLeagueServiceImpl implements LeagueCrawlerService {

    
    private static final Log log = LogFactory.getLog(ChampionshipLeagueServiceImpl.class);

    @Override
    public void crawler(String url, String seasonNo) {
        
        log.info("英冠联赛爬虫服务启动");
        log.info("赛季编号："+ seasonNo +"url:" + url);
        //英冠联赛46轮
        for(int i=1; i<=46; i++){
            String roundUrl = url + i + "/";
            log.info("第"+i+"轮：url"+roundUrl);
            SeasonFactory factory = new SeasonFactory();
            try {
                factory.createSeason(seasonNo).crawler(roundUrl, seasonNo);
            } catch (IOException e) {
                continue;
            }
        }

    }

}
