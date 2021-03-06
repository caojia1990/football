package com.eastng.football.service.match.businessImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
import com.eastng.football.service.support.ScoreBoardFactory;
import com.eastng.football.util.BeanUtils;
import com.eastng.framework.common.utils.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
        
        LeagueSeason record = new LeagueSeason();
        record.setSeasonNo(seasonNo);
        
        List<LeagueSeason> list = this.leagueSeasonMapper.selectByCondition(record);
        
        SeasonVo seasonVo = null;
        
        if(list != null && list.size() >0){
            seasonVo = new SeasonVo();
            BeanUtils.copyProperties(list.get(0), seasonVo);
        }
        
        return seasonVo;
    }
    
    /**
     * 更新积分榜
     * @param seasonNo 赛季编号
     * @throws FootBallBizException 
     */
    @Override
    public void updateScoreBoard(String seasonNo, Integer round) throws FootBallBizException{
        
        LeagueSeason record = new LeagueSeason();
        record.setSeasonNo(seasonNo);
        
        List<LeagueSeason> list = leagueSeasonMapper.selectByCondition(record);
        
        if(list == null || list.size() <=0){
            logger.info("没有查到赛季信息");
            return;
        }
        
        LeagueInfo leagueInfo = this.leagueInfoMapper.selectByLeagueNo(list.get(0).getLeagueNo());
        
        if(leagueInfo == null){
            logger.warn("赛事编号不存在");
            throw new FootBallBizException("", "赛事编号不存在");
        }
        
        ScoreBoardFactory factory = new ScoreBoardFactory();
        factory.createScoreBoard(seasonNo).update(seasonNo, round);
        
    }
    
    @Override
    public List<TeamSeasonScoreVO> queryScoreBoard(String seasonNo) {
        
        List<TeamSeasonScore> scoreBoard = this.teamSeasonScoreMapper.selectScoreBoardBySeasonNo(seasonNo);
        
        List<TeamSeasonScoreVO> scoreBoardVo = null;
        if(scoreBoard.size()>0){
            
            scoreBoardVo = BeanUtils.copyList(scoreBoard, TeamSeasonScoreVO.class);
        }
        
        return scoreBoardVo;
    }

    @Override
    public List<SeasonVo> querySeasonByLeagueNo(String leagueNo) {
        LeagueSeason record = new LeagueSeason();
        record.setLeagueNo(leagueNo);
        
        List<LeagueSeason> list = this.leagueSeasonMapper.selectByCondition(record);
        
        List<SeasonVo> seasonVos = null;
        if(list != null && list.size() >0){
            seasonVos = BeanUtil.copyList(list, SeasonVo.class);
        }
        return seasonVos;
    }

	@Override
	public List<TeamSeasonScoreVO> queryTeamScoreByTeamNoAndSeasonNO(String seasonNo, String teamNo) throws FootBallBizException {

		if(StringUtils.isEmpty(seasonNo)||StringUtils.isEmpty(teamNo)){
			logger.warn("球队编号和赛季编号不能为空");
			throw new FootBallBizException("", "球队编号和赛季编号不能为空");
		}
		TeamSeasonScore score = new TeamSeasonScore();
		score.setTeamNo(teamNo);
		score.setSeasonNo(seasonNo);
		//查询最近五轮联赛排名
		PageHelper.startPage(1, 5);
		List<TeamSeasonScore> list = this.teamSeasonScoreMapper.selectByCondition(score);
		
		List<TeamSeasonScoreVO> teamSeasonScoreVOs = null;
		if(list != null && list.size()>0){
			teamSeasonScoreVOs = BeanUtil.copyList(list, TeamSeasonScoreVO.class);
		}
		return teamSeasonScoreVOs;
	}

}
