package com.eastng.football.service.match.base;

import com.eastng.football.core.entity.match.TeamSeasonScore;

/**
 * 球队积分表数据访问对象
 * @author caoji_000
 */
public interface TeamSeasonScoreDao {
    
    /**
     * 保存或更新积分表
     * @param teamSeasonScore
     */
    void saveOrUpdate(TeamSeasonScore teamSeasonScore);

}
