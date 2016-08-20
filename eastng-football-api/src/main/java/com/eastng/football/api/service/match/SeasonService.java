package com.eastng.football.api.service.match;

import com.eastng.football.api.vo.match.SeasonVo;

public interface SeasonService {
    /**
     * 根据赛季编号查询赛季信息
     * @param seasonNo
     * @return
     */
    SeasonVo querySeasonBySeasonNo(String seasonNo);

}
