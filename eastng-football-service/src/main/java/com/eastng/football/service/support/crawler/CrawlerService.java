package com.eastng.football.service.support.crawler;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.crawler.MatchCrawlerParamVO;

public interface CrawlerService {

    void crawler(MatchCrawlerParamVO paramVO) throws FootBallBizException;
}
