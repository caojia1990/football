package com.eastng.football.service.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eastng.football.api.service.crawler.LeagueCrawlerService;
import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.service.support.impl.ChampionshipLeagueServiceImpl;
import com.eastng.football.service.support.impl.FrenchLigue1ServiceImpl;
import com.eastng.football.service.support.impl.GermanBundesligaServiceImpl;
import com.eastng.football.service.support.impl.ItalianSerieAServiceImpl;
import com.eastng.football.service.support.impl.PermierLeagueServiceImpl;
import com.eastng.football.service.support.impl.SpanishPrimeraDivisionServiceImpl;

public class LeagueFactory {
    
    private static final Log log = LogFactory.getLog(SeasonFactory.class);
    
    @Autowired
    private SeasonService seasonService;
    
    @Autowired
    private LeagueInfoService leagueInfoService;
    
    public LeagueCrawlerService createLeague(String seasonNo){
        log.info("创建爬虫service" + seasonNo);
        
        SeasonVo seasonVo = seasonService.querySeasonBySeasonNo(seasonNo);
        if(seasonVo == null){
            throw new RuntimeException("没有查到此赛季信息："+seasonNo);
        }
        
        LeagueInfoVO leagueInfoVO = leagueInfoService.queryLeagueInfoByLeagueNo(seasonVo.getLeagueNo());
        if(leagueInfoVO == null){
            throw new RuntimeException("没有查到此联赛信息："+seasonVo.getLeagueNo());
        }
        LeagueCrawlerService crawlerService = null;
        
        if(leagueInfoVO.getLeagueNo().equals("001005001")){
          //英超001005001
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService =  new PermierLeagueServiceImpl();
        }else if(leagueInfoVO.getLeagueNo().equals("001005002")){
            //英冠
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = new ChampionshipLeagueServiceImpl();
        }else if (leagueInfoVO.getLeagueNo().equals("001003001")) {
            //德甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = new GermanBundesligaServiceImpl();
        }else if (leagueInfoVO.getLeagueNo().equals("001017001")) {
            //法甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = new FrenchLigue1ServiceImpl();
        }else if (leagueInfoVO.getLeagueNo().equals("001009001")) {
            //意甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = new ItalianSerieAServiceImpl();
        }else if (leagueInfoVO.getLeagueNo().equals("001002001")) {
            //西甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = new SpanishPrimeraDivisionServiceImpl();
        }
        return crawlerService;
    }

}
