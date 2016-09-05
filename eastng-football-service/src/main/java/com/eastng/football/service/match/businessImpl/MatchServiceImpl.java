package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.LeagueSeason;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;
import com.eastng.football.core.entity.match.MatchExample.Criteria;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.LeagueSeasonMapper;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.service.ScoreBoardFactory;
import com.eastng.football.service.match.base.MatchBaseService;
import com.eastng.football.util.BeanUtils;
import com.eastng.football.util.GenerateCodeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("matchService")
public class MatchServiceImpl implements MatchService {
    
    static Logger logger = Logger.getLogger(MatchServiceImpl.class);
    
    @Autowired
    private MatchMapper matchMapper;
    
    @Autowired
    private LeagueInfoMapper leagueInfoMapper;
    
    @Autowired
    private LeagueSeasonMapper leagueSeasonMapper;
    
    @Autowired
    private TeamSeasonScoreMapper teamSeasonScoreMapper;

    @Autowired
    private MatchBaseService matchBaseService;
    /**
     * 根据条件查询比赛信息
     * @param paramVO
     * @return
     */
    public PageResult<MatchVO> queryMatchSchedule(QueryMatchParamVO paramVO) {
        
        logger.info("查询比赛信息列表入参："+paramVO.toString());
        PageResult<MatchVO> result = new PageResult<MatchVO>();
        
        MatchExample example = new MatchExample();
        example.setOrderByClause("match_time asc");
        Criteria criteria = example.createCriteria();
        
        //开始时间
        if(!StringUtils.isEmpty(paramVO.getBeginDate())){
            criteria.andMatchTimeGreaterThanOrEqualTo(paramVO.getBeginDate());
        }
        //结束时间
        if(!StringUtils.isEmpty(paramVO.getEndDate())){
            criteria.andMatchTimeLessThan(paramVO.getEndDate());
        }
        
        //联赛编号
        if(!StringUtils.isEmpty(paramVO.getLeagueNo())){
            criteria.andLeagueNoEqualTo(paramVO.getLeagueNo());
        }
        //轮次
        if(!StringUtils.isEmpty(paramVO.getRound())){
            criteria.andRoundLessThan(paramVO.getRound());
        }
        //比赛状态
        if(!StringUtils.isEmpty(paramVO.getMatchStatus())){
            criteria.andMatchStatusEqualTo(paramVO.getMatchStatus());
        }
        
        PageHelper.startPage(paramVO.getPage(), paramVO.getRows());
        List<Match> list = this.matchMapper.selectByExample(example);
        Page<Match> page = (Page)list;
        
        result.setTotal(page.getTotal());
        List<MatchVO> resultList = BeanUtils.copyList(list, MatchVO.class);
        result.setResult(resultList);
        
        return result;
    }

    /**
     * 查询两队交战记录
     * @param paramVO
     * @return
     * @throws FootBallBizException 
     */
    public PageResult<MatchVO> queryMatchHistory(QueryMatchParamVO paramVO) throws FootBallBizException {
        
        logger.info("查询两队交战记录入参："+ToStringBuilder.reflectionToString(paramVO, ToStringStyle.MULTI_LINE_STYLE));
        PageResult<MatchVO> result = new PageResult<MatchVO>();
        
        Match record = new Match();
        
        if(!StringUtils.isEmpty(paramVO.getHostTeamNo())&&!StringUtils.isEmpty(paramVO.getGuestTeamNo())){
            record.setHostTeamNo(paramVO.getHostTeamNo());
            record.setGuestTeamNo(paramVO.getGuestTeamNo());
        }else{
            logger.info("两支球队编号不能为空");
            throw new FootBallBizException("", "两支球队编号不能为空");
        }
        //开始时间
        if(!StringUtils.isEmpty(paramVO.getBeginDate())){
            record.setMatchTime(paramVO.getBeginDate());
        }
        
        //联赛编号
        if(!StringUtils.isEmpty(paramVO.getLeagueNo())){
            record.setLeagueNo(paramVO.getLeagueNo());
        }
        
        //比赛状态
        record.setMatchStatus(CommonConstant.MATCH_STATUS_END);
        
        PageHelper.startPage(paramVO.getPage(), paramVO.getRows());
        List<Match> list = this.matchMapper.queryMatchHistory(record);
        Page<Match> page = (Page)list;
        
        result.setTotal(page.getTotal());
        
        List<MatchVO> resultList = BeanUtils.copyList(list, MatchVO.class);
        
        result.setResult(resultList);
        return result;
    }

    /**
     * 保存比赛信息
     * @param matchVO
     * @return
     * @throws FootBallBizException 
     */
    public String saveMatch(MatchVO matchVO) throws FootBallBizException{
        
        logger.info("保存比赛信息saveMatch入参："+ToStringBuilder.reflectionToString(matchVO, ToStringStyle.MULTI_LINE_STYLE));
        
        Match record = new Match();
        BeanUtils.copyProperties(matchVO, record);
        String matchNo = this.matchBaseService.saveOrUpdate(record);
        
        return matchNo;
    }
    
    public Integer saveMatchList(List<MatchVO> list){
        List<Match> records = new ArrayList<Match>();
        for(MatchVO matchVO:list){
            Match record = new Match();
            BeanUtils.copyProperties(matchVO, record);
            record.setMatchNo(GenerateCodeUtil.generateMatchNo("YC"));
            records.add(record);
        }
        return this.matchMapper.batchInsert(records);
    }
    

    /**
     * 根据比赛编号查询比赛详情
     * @param matchNo
     * @return
     */
    public MatchVO queryMatchByMatchNo(String matchNo) {
        this.matchMapper.selectMatchByMatchNo("123");
        return null;
    }


    /**
     * 查询最近的战况
     * @param paramVO
     * @return
     * @throws FootBallBizException 
     */
    public PageResult<MatchVO> queryRecentMatch(QueryMatchParamVO paramVO) throws FootBallBizException {
        logger.info("查询最近的战况入参："+ToStringBuilder.reflectionToString(paramVO, ToStringStyle.MULTI_LINE_STYLE));
        PageResult<MatchVO> result = new PageResult<MatchVO>();
        
        Match record = new Match();
        
        if(StringUtils.isEmpty(paramVO.getHostTeamNo())){
            logger.info("球队编号不能为空");
            throw new FootBallBizException("", "球队编号不能为空");
        }
        record.setHostTeamNo(paramVO.getHostTeamNo());
        
        //开始时间
        if(!StringUtils.isEmpty(paramVO.getBeginDate())){
            record.setMatchTime(paramVO.getBeginDate());
        }
        
        //联赛编号
        if(!StringUtils.isEmpty(paramVO.getLeagueNo())){
            record.setLeagueNo(paramVO.getLeagueNo());
        }
        
        //比赛状态
        record.setMatchStatus(CommonConstant.MATCH_STATUS_END);
        
        PageHelper.startPage(paramVO.getPage(), paramVO.getRows());
        List<Match> list = this.matchMapper.queryRecentMatch(record);
        Page<Match> page = (Page)list;
        
        result.setTotal(page.getTotal());
        
        List<MatchVO> resultList = BeanUtils.copyList(list, MatchVO.class);
        
        result.setResult(resultList);
        return result;
    }
    
}
