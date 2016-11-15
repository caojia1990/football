package com.eastng.football.web.view.lottery;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OddsResultVO {

    /**
     * 比赛编号
     */
    private String matchNo;

    /**
     * 公司名称
     */
    private String company;


    /**
     * 变赔时间
     */
    private Date changeTime;
    
    /**
     * 距离开赛时间
     */
    private String timeLeft;
    
    /**
     * 数字方式显示距离开赛时间
     */
    private String timeLeftDigit;


    /**
     * 胜
     */
    private BigDecimal win;

    /**
     * 平
     */
    private BigDecimal draw;

    /**
     * 负
     */
    private BigDecimal lose;

    /**
     * 是否初赔
     */
    private String firstOdds;

    /**
     * @return the matchNo
     */
    public String getMatchNo() {
        return matchNo;
    }

    /**
     * @param matchNo the matchNo to set
     */
    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the changeTime
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * @param changeTime the changeTime to set
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * @return the timeLeft
     */
    public String getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft the timeLeft to set
     */
    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return the win
     */
    public BigDecimal getWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(BigDecimal win) {
        this.win = win;
    }

    /**
     * @return the draw
     */
    public BigDecimal getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(BigDecimal draw) {
        this.draw = draw;
    }

    /**
     * @return the lose
     */
    public BigDecimal getLose() {
        return lose;
    }

    /**
     * @param lose the lose to set
     */
    public void setLose(BigDecimal lose) {
        this.lose = lose;
    }

    /**
     * @return the firstOdds
     */
    public String getFirstOdds() {
        return firstOdds;
    }

    /**
     * @param firstOdds the firstOdds to set
     */
    public void setFirstOdds(String firstOdds) {
        this.firstOdds = firstOdds;
    }

    public String getTimeLeftDigit() {
        return timeLeftDigit;
    }

    public void setTimeLeftDigit(String timeLeftDigit) {
        this.timeLeftDigit = timeLeftDigit;
    }
    
    
}
