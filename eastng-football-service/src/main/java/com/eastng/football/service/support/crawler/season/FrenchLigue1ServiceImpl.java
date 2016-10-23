package com.eastng.football.service.support.crawler.season;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.crawler.MatchCrawlerParamVO;
import com.eastng.football.service.support.SeasonFactory;
import com.eastng.football.service.support.crawler.CrawlerService;

@Service("frenchLigue1Service")
public class FrenchLigue1ServiceImpl implements CrawlerService {

    
    private static final Logger log = Logger.getLogger(FrenchLigue1ServiceImpl.class);
    
    @Autowired
    private SeasonFactory seasonFactory;

    @Override
    public void crawler(MatchCrawlerParamVO paramVO){
        
        String url = paramVO.getUrl();
        String seasonNo = paramVO.getSeasonNo();
        
        log.info("法甲联赛爬虫服务启动");
        log.info("赛季编号："+ seasonNo +"url:" + url);
        //法甲联赛38轮
        if(!StringUtils.isEmpty(paramVO.getBeginRound())&&!StringUtils.isEmpty(paramVO.getEndRound())){
            Integer beginRound = Integer.parseInt(paramVO.getBeginRound());
            Integer endRound = Integer.parseInt(paramVO.getEndRound());
            for(int i=beginRound; i<=endRound; i++){
                String roundUrl = url + i + "/";
                log.info("第"+i+"轮：url"+roundUrl);
                paramVO.setUrl(roundUrl);
                try {
                    seasonFactory.createSeason(seasonNo).crawler(paramVO);
                } catch (FootBallBizException e) {
                    log.error("获取失败，URL： "+ roundUrl,e);
                    continue;
                }
            }
        }else {
            for(int i=1; i<=38; i++){
                String roundUrl = url + i + "/";
                log.info("第"+i+"轮：url"+roundUrl);
                paramVO.setUrl(roundUrl);
                try {
                    seasonFactory.createSeason(seasonNo).crawler(paramVO);
                } catch (FootBallBizException e) {
                    log.error("获取失败，URL： "+ roundUrl,e);
                    continue;
                }
            }
        }
    }

}
