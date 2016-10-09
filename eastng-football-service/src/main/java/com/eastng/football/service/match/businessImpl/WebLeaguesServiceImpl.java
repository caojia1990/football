package com.eastng.football.service.match.businessImpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.exception.ExceptionCode;
import com.eastng.football.api.exception.FootballWebException;
import com.eastng.football.api.service.match.WebLeaguesService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.LeagueInfoExample;
import com.eastng.football.core.entity.match.LeagueSeason;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.LeagueSeasonMapper;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.util.BeanUtils;

/**
 * 
 * @author laughing
 * @date 2016-09-24 15:49:47
 * @description
 */
@Service
public class WebLeaguesServiceImpl implements WebLeaguesService {

	@Autowired
	private LeagueInfoMapper leagueInfoMapper;

	@Autowired
	private LeagueSeasonMapper leagueSeasonMapper;

	@Autowired
	private TeamSeasonScoreMapper teamSeasonScoreMapper;
	
	@Autowired
	private MatchMapper matchMapper;

	/**
	 * 查询所有的联赛信息
	 * 
	 * @return List<LeagueInfoVO>
	 */
	@Override
	public List<LeagueInfoVO> findAllLeagueInfo() throws FootballWebException {
		LeagueInfoExample example = new LeagueInfoExample();
		// Criteria criteria = example.createCriteria();
		// example.setOrderByClause("");
		List<LeagueInfo> leagueInfos = leagueInfoMapper.selectByExample(example);
		List<LeagueInfoVO> leagueInfoVOs = new ArrayList<LeagueInfoVO>();
		if (leagueInfos != null && leagueInfos.size() > 0){
			for (LeagueInfo leagueInfo : leagueInfos) {
				LeagueInfoVO infoVO = new LeagueInfoVO();
				BeanUtils.copyProperties(leagueInfo, infoVO);
				leagueInfoVOs.add(infoVO);
			}
		}else{
			throw new FootballWebException(ExceptionCode.MISS_LEAGUES);
		}
		return leagueInfoVOs;
	}

	
	@Override
	public Map<String, Object> findLeagueInfo(String leagueNo) throws FootballWebException {
		LeagueSeason leagueSeason = findMaxLeagueSeasonBySeasonNo(leagueNo);
		String seasonNo = leagueSeason.getSeasonNo();
		List<MatchVO> matchList = findMatchBySeasonNo(seasonNo, leagueNo);
		List<TeamSeasonScoreVO> teamSeasonScoreList = findTeamSeasonScoreBySeasonNo(seasonNo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("match", matchList);
		result.put("teamSeasonScore", teamSeasonScoreList);
		return result;
	}

	/**
	 * 
	 * @param seasonNo
	 * @return
	 * @throws FootballWebException
	 */
	private List<TeamSeasonScoreVO> findTeamSeasonScoreBySeasonNo(String seasonNo) throws FootballWebException {
		List<TeamSeasonScore> list = teamSeasonScoreMapper.selectScoreBoardBySeasonNo(seasonNo);
		List<TeamSeasonScoreVO> voList = new ArrayList<TeamSeasonScoreVO>();
		if (list != null && list.size() > 0) {
			for (TeamSeasonScore teamSeasonScore : list) {
				TeamSeasonScoreVO scoreVO = new TeamSeasonScoreVO();
				BeanUtils.copyProperties(teamSeasonScore, scoreVO);
				voList.add(scoreVO);
			}
		} else {
			throw new FootballWebException(ExceptionCode.MISS_TEAMSEASONSCORE);
		}
		return voList;
	}

	
	/**
	 * 查找赛季 的比赛
	 * 
	 * @param seasonNo
	 * @param leagueNo
	 * @return
	 * @throws FootballWebException
	 */
	private List<MatchVO> findMatchBySeasonNo(String seasonNo , String leagueNo){
		List<MatchVO> voList = new ArrayList<MatchVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		StringBuilder dateBuilder = new StringBuilder(sdf.format(date)).append(" 00:00:00");
		Map<String, String> params = new HashMap<String, String>();
		params.put("startDate", dateBuilder.toString());
		params.put("seasonNo", seasonNo);
		List<Match> matchList = matchMapper.selectMatchByBeginDateAndSeasonNo(params);
		if(matchList == null || matchList.size() <= 0){
			params.put("endDate", dateBuilder.toString());
			// 有这种可能性 表示  这个赛季还没有比赛数据或者结束了
			// 这个是desc 排序的数据 如果有需要 倒过来排序
			matchList = matchMapper.selectMatchByEndDateAndLeagueNo(params);
		}
		if(matchList != null)
			for(Match match : matchList){
				MatchVO matchVO = new MatchVO();
				BeanUtils.copyProperties(match, matchVO);
				voList.add(matchVO);
			}
		return voList;
	}
	/**
	 * 根据 leagueNo 获取最近的联赛信息
	 * 
	 * @param leagueNo
	 * @return
	 * @throws FootballWebException
	 * @description 现在没法知道到底是那个赛季，只好采取取数据库中最大的，到以后可以知道可以直接查出来
	 */
	private LeagueSeason findMaxLeagueSeasonBySeasonNo(String leagueNo) throws FootballWebException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("leagueNo", leagueNo);
		LeagueSeason leagueSeason = leagueSeasonMapper.selectMaxLeagueSeasonByLeagueNo(params);
		if (leagueSeason == null) {
			throw new FootballWebException(ExceptionCode.MISS_SEASON);
		}
		return leagueSeason;
	}

}
