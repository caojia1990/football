package com.eastng.football.service.support;

public interface ScoreBoardService {
    
    /**
     * 更新赛季某一轮积分榜
     * @param seasonNo 赛季编号
     * @param round 轮次
     */
    void update(String seasonNo, Integer round);

}
