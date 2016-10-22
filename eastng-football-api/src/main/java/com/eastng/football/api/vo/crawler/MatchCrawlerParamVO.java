package com.eastng.football.api.vo.crawler;

import java.io.Serializable;

public class MatchCrawlerParamVO implements Serializable{

    private static final long serialVersionUID = 3351639692117912835L;

    /**
     * 爬取地址
     */
    private String url;
    
    /**
     * 赛季编号
     */
    private String seasonNo;
    
    /**
     * 起始轮次
     */
    private String beginRound;
    
    /**
     * 结束轮次
     */
    private String endRound;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(String seasonNo) {
        this.seasonNo = seasonNo;
    }

    public String getBeginRound() {
        return beginRound;
    }

    public void setBeginRound(String beginRound) {
        this.beginRound = beginRound;
    }

    public String getEndRound() {
        return endRound;
    }

    public void setEndRound(String endRound) {
        this.endRound = endRound;
    }
    
}
