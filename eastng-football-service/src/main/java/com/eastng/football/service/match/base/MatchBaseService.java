package com.eastng.football.service.match.base;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.core.entity.match.Match;

public interface MatchBaseService {
    
    /**
     * 保存或更新比赛信息
     * @param matchVO
     * @return
     * @throws FootBallBizException 
     */
    String saveOrUpdate(Match match) throws FootBallBizException;

}
