package com.eastng.football.service.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.service.support.crawler.CrawlerService;
import com.eastng.football.service.support.crawler.season.ChampionshipLeagueServiceImpl;
import com.eastng.football.service.support.crawler.season.FrenchLigue1ServiceImpl;
import com.eastng.football.service.support.crawler.season.GermanBundesligaServiceImpl;
import com.eastng.football.service.support.crawler.season.ItalianSerieAServiceImpl;
import com.eastng.football.service.support.crawler.season.PermierLeagueServiceImpl;
import com.eastng.football.service.support.crawler.season.SpanishPrimeraDivisionServiceImpl;
import com.eastng.football.util.SpringContextUtil;

@Component("leagueFactory")
public class LeagueFactory {
    
    private static final Log log = LogFactory.getLog(SeasonFactory.class);
    
    @Autowired
    private SeasonService seasonService;
    
    @Autowired
    private LeagueInfoService leagueInfoService;
    
    public CrawlerService createLeague(String seasonNo){
        log.info("创建爬虫service" + seasonNo);
        
        SeasonVo seasonVo = seasonService.querySeasonBySeasonNo(seasonNo);
        if(seasonVo == null){
            throw new RuntimeException("没有查到此赛季信息："+seasonNo);
        }
        
        LeagueInfoVO leagueInfoVO = leagueInfoService.queryLeagueInfoByLeagueNo(seasonVo.getLeagueNo());
        if(leagueInfoVO == null){
            throw new RuntimeException("没有查到此联赛信息："+seasonVo.getLeagueNo());
        }
        CrawlerService crawlerService = null;
        
        if(leagueInfoVO.getLeagueNo().equals("001005001")){
          //英超001005001
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService =  (CrawlerService) SpringContextUtil.getBean("permierLeagueService");
        }else if(leagueInfoVO.getLeagueNo().equals("001005002")){
            //英冠
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = (CrawlerService) SpringContextUtil.getBean("ChampionshipLeagueService");
        }else if (leagueInfoVO.getLeagueNo().equals("001003001")) {
            //德甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = (CrawlerService) SpringContextUtil.getBean("germanBundesligaService");
        }else if (leagueInfoVO.getLeagueNo().equals("001017001")) {
            //法甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = (CrawlerService) SpringContextUtil.getBean("frenchLigue1Service");
        }else if (leagueInfoVO.getLeagueNo().equals("001009001")) {
            //意甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = (CrawlerService) SpringContextUtil.getBean("italianSerieAService");
        }else if (leagueInfoVO.getLeagueNo().equals("001002001")) {
            //西甲
            log.info("创建"+leagueInfoVO.getLeagueName()+"完成!");
            crawlerService = (CrawlerService) SpringContextUtil.getBean("spanishPrimeraDivisionService");
        }
        return crawlerService;
    }

}
