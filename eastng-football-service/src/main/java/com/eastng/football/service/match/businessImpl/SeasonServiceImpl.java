package com.eastng.football.service.match.businessImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.LeagueSeason;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.LeagueSeasonMapper;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.service.ScoreBoardFactory;
import com.eastng.football.util.BeanUtils;

@Service("seasonService")
public class SeasonServiceImpl implements SeasonService {

    static Logger logger = Logger.getLogger(SeasonServiceImpl.class); 
    
    @Autowired
    private LeagueSeasonMapper leagueSeasonMapper;
    
    @Autowired
    private TeamSeasonScoreMapper teamSeasonScoreMapper;
    
    @Autowired
    private LeagueInfoMapper leagueInfoMapper;
    
    @Override
    public SeasonVo querySeasonBySeasonNo(String seasonNo) {
        LeagueSeason record = this.leagueSeasonMapper.selectBySeasonNo(seasonNo);
        SeasonVo seasonVo = new SeasonVo();
        BeanUtils.copyProperties(record, seasonVo);
        return seasonVo;
    }
    
    /**
     * 更新积分榜
     * @param seasonNo 赛季编号
     * @throws FootBallBizException 
     */
    @Override
    public void updateScoreBoard(String seasonNo, Integer round) throws FootBallBizException{
        
        
        LeagueSeason leagueSeason = leagueSeasonMapper.selectBySeasonNo(seasonNo);
        
        if(leagueSeason == null){
            logger.info("没有查到赛季信息");
            return;
        }
        
        LeagueInfo leagueInfo = this.leagueInfoMapper.selectByLeagueNo(leagueSeason.getLeagueNo());
        
        if(leagueInfo == null){
            logger.error("赛事编号不存在");
            throw new FootBallBizException("", "赛事编号不存在");
        }
        
        ScoreBoardFactory factory = new ScoreBoardFactory();
        factory.createScoreBoard(seasonNo).update(seasonNo, round);
        
    }
    
    @Override
    public List<TeamSeasonScoreVO> queryScoreBoard(String seasonNo) {
        
        List<TeamSeasonScore> scoreBoard = this.teamSeasonScoreMapper.selectScoreBoardBySeasonNo(seasonNo);
        
        List<TeamSeasonScoreVO> scoreBoardVo = BeanUtils.copyList(scoreBoard, TeamSeasonScoreVO.class);
        
        return scoreBoardVo;
    }

}
