package com.eastng.football.service.match.base;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.core.entity.match.TeamSeasonScore;

/**
 * 球队积分表数据访问对象
 * @author caoji_000
 */
public interface TeamSeasonScoreBaseService {
    
    /**
     * 保存或更新积分表
     * @param teamSeasonScore
     * @throws FootBallBizException 
     */
    void saveOrUpdate(TeamSeasonScore teamSeasonScore) throws FootBallBizException;

}
