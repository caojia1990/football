package com.eastng.football.service.support;

import com.eastng.football.api.exception.FootBallBizException;

public interface ScoreBoardService {
    
    /**
     * 更新赛季某一轮积分榜
     * @param seasonNo 赛季编号
     * @param round 轮次
     * @throws FootBallBizException 
     */
    void update(String seasonNo, Integer round) throws FootBallBizException;

}
