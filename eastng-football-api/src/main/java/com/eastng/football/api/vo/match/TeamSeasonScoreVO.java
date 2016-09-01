package com.eastng.football.api.vo.match;

import java.io.Serializable;

/**
 * 联赛积分榜
 * @author caoji_000
 *
 */
public class TeamSeasonScoreVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 9040099350219908486L;

    /**
     * 球队编号
     */
    private String teamNo;

    /**
     * 球队名称
     */
    private String teamShortName;

    /**
     * 联赛编号
     */
    private String leagueNo;

    /**
     * 赛季编号
     */
    private String seasonNo;

    /**
     * 赛季名称
     */
    private String seasonName;

    /**
     * 轮次
     */
    private Integer round;

    /**
     * 胜利场次
     */
    private Integer win;

    /**
     * 平场次
     */
    private Integer draw;

    /**
     * 失败场次
     */
    private Integer lose;

    /**
     * 进球数
     */
    private Integer goals;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 失球数
     */
    private Integer fumble;

    /**
     * 主场已赛场次
     */
    private Integer hostHaveMatch;

    /**
     * 主场胜利场次
     */
    private Integer hostWin;

    /**
     * 主场平局场次
     */
    private Integer hostDraw;

    /**
     * 主场失败场次
     */
    private Integer hostLose;

    /**
     * 主场进球数
     */
    private Integer hostGoals;

    /**
     * 主场失球数
     */
    private Integer hostFumble;

    /**
     * 主场积分
     */
    private Integer hostPoints;

    /**
     * 客场已赛场次
     */
    private Integer guestHaveMatch;

    /**
     * 客场胜利场次
     */
    private Integer guestWin;

    /**
     * 客场平局场次
     */
    private Integer guestDraw;

    /**
     * 客场失败场次
     */
    private Integer guestLose;

    /**
     * 客场进球数
     */
    private Integer guestGoals;

    /**
     * 客场失球数
     */
    private Integer guestFumble;

    /**
     * 客场积分
     */
    private Integer guestPoints;

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
    }

    public String getLeagueNo() {
        return leagueNo;
    }

    public void setLeagueNo(String leagueNo) {
        this.leagueNo = leagueNo;
    }

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

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getFumble() {
        return fumble;
    }

    public void setFumble(Integer fumble) {
        this.fumble = fumble;
    }

    public Integer getHostHaveMatch() {
        return hostHaveMatch;
    }

    public void setHostHaveMatch(Integer hostHaveMatch) {
        this.hostHaveMatch = hostHaveMatch;
    }

    public Integer getHostWin() {
        return hostWin;
    }

    public void setHostWin(Integer hostWin) {
        this.hostWin = hostWin;
    }

    public Integer getHostDraw() {
        return hostDraw;
    }

    public void setHostDraw(Integer hostDraw) {
        this.hostDraw = hostDraw;
    }

    public Integer getHostLose() {
        return hostLose;
    }

    public void setHostLose(Integer hostLose) {
        this.hostLose = hostLose;
    }

    public Integer getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(Integer hostGoals) {
        this.hostGoals = hostGoals;
    }

    public Integer getHostFumble() {
        return hostFumble;
    }

    public void setHostFumble(Integer hostFumble) {
        this.hostFumble = hostFumble;
    }

    public Integer getHostPoints() {
        return hostPoints;
    }

    public void setHostPoints(Integer hostPoints) {
        this.hostPoints = hostPoints;
    }

    public Integer getGuestHaveMatch() {
        return guestHaveMatch;
    }

    public void setGuestHaveMatch(Integer guestHaveMatch) {
        this.guestHaveMatch = guestHaveMatch;
    }

    public Integer getGuestWin() {
        return guestWin;
    }

    public void setGuestWin(Integer guestWin) {
        this.guestWin = guestWin;
    }

    public Integer getGuestDraw() {
        return guestDraw;
    }

    public void setGuestDraw(Integer guestDraw) {
        this.guestDraw = guestDraw;
    }

    public Integer getGuestLose() {
        return guestLose;
    }

    public void setGuestLose(Integer guestLose) {
        this.guestLose = guestLose;
    }

    public Integer getGuestGoals() {
        return guestGoals;
    }

    public void setGuestGoals(Integer guestGoals) {
        this.guestGoals = guestGoals;
    }

    public Integer getGuestFumble() {
        return guestFumble;
    }

    public void setGuestFumble(Integer guestFumble) {
        this.guestFumble = guestFumble;
    }

    public Integer getGuestPoints() {
        return guestPoints;
    }

    public void setGuestPoints(Integer guestPoints) {
        this.guestPoints = guestPoints;
    }
    
    
}
