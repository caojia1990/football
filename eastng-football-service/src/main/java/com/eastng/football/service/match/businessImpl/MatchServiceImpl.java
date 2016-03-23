package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StringUtils;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;
import com.eastng.football.core.entity.match.MatchExample.Criteria;
import com.eastng.football.core.entity.match.TeamLeagueSeason;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamCupSeasonMapper;
import com.eastng.football.core.service.match.persistence.TeamLeagueSeasonMapper;
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
	private TeamLeagueSeasonMapper leagueSeasonMapper;
	
	@Autowired
	private TeamCupSeasonMapper teamCupSeasonMapper;
	
	@Autowired
	private LeagueInfoMapper leagueInfoMapper;

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
		
		List<MatchVO> resultList = new ArrayList<MatchVO>();
		for(Match match:list){
			MatchVO matchVO = new MatchVO();
			BeanUtils.copyProperties(match, matchVO);
			resultList.add(matchVO);
		}
		
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
		
		List<MatchVO> resultList = new ArrayList<MatchVO>();
		for(Match match:list){
			MatchVO matchVO = new MatchVO();
			BeanUtils.copyProperties(match, matchVO);
			resultList.add(matchVO);
		}
		
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
		
		String matchNo = null;
		Match matchResult = this.queryUniqueMatch(matchVO);
		//没有则保存
		if(matchResult == null){
			//比赛状态 0：未开始 1：比赛中 2：已结束
			String matchStatus = matchVO.getMatchStatus();
			if(StringUtils.isEmpty(matchStatus)){
				logger.error("比赛状态不能为空");
				throw new FootBallBizException("", "比赛状态不能为空");
			}
			
			String leagueNo = matchVO.getLeagueNo();
			if(StringUtils.isEmpty(leagueNo)){
				logger.error("赛事编号不能为空");
				throw new FootBallBizException("", "赛事编号不能为空");
			}
			LeagueInfo leagueInfo= this.leagueInfoMapper.selectByLeagueNo(leagueNo);
			if(leagueInfo ==null ){
				logger.error("赛事编号不存在");
				throw new FootBallBizException("", "赛事编号不存在");
			}
			
			Match match = new Match();
			BeanUtils.copyProperties(matchVO, match);
			match.setMatchNo(GenerateCodeUtil.generateMatchNo(leagueInfo.getLeagueNo()));
			matchNo = match.getMatchNo();
			int result = this.matchMapper.saveMatch(match);
		}else {
			//若库里存在，则根据状态来更新
			if(!matchResult.getMatchStatus().equals(matchVO.getMatchStatus())){
				Match record = new Match();
				BeanUtils.copyProperties(matchVO, record);
				record.setId(matchResult.getId());
				logger.info("更新比赛信息入参"+ToStringBuilder.reflectionToString(record, ToStringStyle.MULTI_LINE_STYLE));
				this.matchMapper.updateByPrimaryKeySelective(record);
			}
			matchNo = matchResult.getMatchNo();
		}
		
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
	 * 更新积分榜
	 * @param leagueNo 联赛编号
	 * @param seasonName 赛季名称
	 * @throws FootBallBizException 
	 */
	public void updateScoreBoard(String leagueNo,String seasonName) throws FootBallBizException{
		LeagueInfo leagueInfo = this.leagueInfoMapper.selectByLeagueNo(leagueNo);
		if(leagueInfo == null){
			logger.error("赛事编号不存在");
			throw new FootBallBizException("", "赛事编号不存在");
		}
		if(CommonConstant.LEAGUE_INFO_EVENT_TYPE_LEAGUE.equals(leagueInfo.getEventType())){
			MatchExample example = new MatchExample();
			example.setOrderByClause("ROUND ASC");
			example.createCriteria().andLeagueNoEqualTo(leagueNo)
				.andSeasonNameEqualTo(seasonName);
			List<Match> list = this.matchMapper.selectByExample(example);
			this.matchMapper.deleteByExample(example);
			for(Match match:list){
				//主队积分信息
				TeamLeagueSeason season = new TeamLeagueSeason();
				season.setLeagueNo(leagueNo);
				season.setSeasonName(seasonName);
				season.setTeamNo(match.getHostTeamNo());
				season.setTeamShortName(match.getHostShortName());
				season.setRound(match.getRound());
				int point = 0;
				if(match.getHostGoal()>match.getGuestGoal()){
					point = 3;
				}
			}
		}
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
	 * 根据必要条件查询唯一一条比赛记录
	 * @param matchVOs
	 * @return
	 * @throws FootBallBizException 
	 */
	private Match queryUniqueMatch(MatchVO matchVO) throws FootBallBizException {
		logger.info("根据必要条件查询唯一一条比赛记录queryUniqueMatch入参"+ToStringBuilder.reflectionToString(matchVO, ToStringStyle.MULTI_LINE_STYLE));
		if(StringUtils.isEmpty(matchVO)){
			logger.info("入参为空");
			throw new FootBallBizException("", "入参为空");
		}
		if(StringUtils.isEmpty(matchVO.getHostTeamNo())){
			logger.info("主队编号为空");
			throw new FootBallBizException("", "主队编号为空");
		}
		if(StringUtils.isEmpty(matchVO.getGuestTeamNo())){
			logger.info("客队编号为空");
			throw new FootBallBizException("", "客队编号为空");
		}
		if(StringUtils.isEmpty(matchVO.getLeagueNo())){
			logger.info("联赛编号为空");
			throw new FootBallBizException("", "联赛编号为空");
		}
		if(StringUtils.isEmpty(matchVO.getSeasonName())){
			logger.info("赛季名称为空");
			throw new FootBallBizException("", "赛季名称为空");
		}
		if(StringUtils.isEmpty(matchVO.getMatchTime())){
			logger.info("比赛时间为空");
			throw new FootBallBizException("", "比赛时间为空");
		}
		Match record = new Match();
		record.setHostTeamNo(matchVO.getHostTeamNo());
		record.setGuestTeamNo(matchVO.getGuestTeamNo());
		record.setLeagueNo(matchVO.getLeagueNo());
		record.setSeasonName(matchVO.getSeasonName());
		record.setMatchTime(matchVO.getMatchTime());
		
		List<Match> list = this.matchMapper.selectByCondition(record);
		
		Match match = new Match();
		if(list != null && list.size() >0){
			match = list.get(0);
		}else {
			match = null;
		}
		logger.info("根据必要条件查询唯一一条比赛记录queryUniqueMatch返回"+ToStringBuilder.reflectionToString(match, ToStringStyle.MULTI_LINE_STYLE));
		return match;
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
		
		List<MatchVO> resultList = new ArrayList<MatchVO>();
		for(Match match:list){
			MatchVO matchVO = new MatchVO();
			BeanUtils.copyProperties(match, matchVO);
			resultList.add(matchVO);
		}
		
		result.setResult(resultList);
		return result;
	}
	
}
