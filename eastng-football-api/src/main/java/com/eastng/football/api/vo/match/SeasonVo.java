package com.eastng.football.api.vo.match;

import java.io.Serializable;
import java.util.Date;

public class SeasonVo implements Serializable {

    /**
     * 赛季编号
     */
    private String seasonNo;

    /**
     * 赛季名称
     */
    private String seasonName;

    /**
     * 联赛编号
     */
    private String leagueNo;

    /**
     * 联赛开始时间
     */
    private Date seasonBeginDate;

    /**
     * 联赛结束时间
     */
    private Date seasonEndDate;

    public String getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(String seasonNo) {
        this.seasonNo = seasonNo;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getLeagueNo() {
        return leagueNo;
    }

    public void setLeagueNo(String leagueNo) {
        this.leagueNo = leagueNo;
    }

    public Date getSeasonBeginDate() {
        return seasonBeginDate;
    }

    public void setSeasonBeginDate(Date seasonBeginDate) {
        this.seasonBeginDate = seasonBeginDate;
    }

    public Date getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(Date seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }
    
    
}
