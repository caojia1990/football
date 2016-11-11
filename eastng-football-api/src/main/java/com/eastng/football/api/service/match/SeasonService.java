package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;

public interface SeasonService {
    /**
     * 根据赛季编号查询赛季信息
     * @param seasonNo
     * @return
     */
    SeasonVo querySeasonBySeasonNo(String seasonNo);
    
    /**
     * 根据联赛编号查询赛季列表
     * @param leagueNo
     * @return
     */
    List<SeasonVo> querySeasonByLeagueNo(String leagueNo);
    
    /**
     * 更新积分榜
     * @param seasonNo 赛季编号
     * @throws FootBallBizException 
     */
    public void updateScoreBoard(String seasonNo, Integer round) throws FootBallBizException;
    
    /**
     * 查询赛季最新积分榜
     * @param teamNo
     * @param SeasonNo
     * @return 
     */
    public List<TeamSeasonScoreVO> queryScoreBoard(String seasonNo);
    
    /**
     * 查询球队某一赛季积分排名情况
     * @param seasonNo
     * @param teamNo
     * @return
     */
    public List<TeamSeasonScoreVO> queryTeamScoreByTeamNoAndSeasonNO(String seasonNo, String teamNo);

}
