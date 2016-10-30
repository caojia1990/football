package com.eastng.football.service.match.businessImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.ExceptionCode;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.exception.FootballWebException;
import com.eastng.football.api.service.match.WebLeaguesService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.api.vo.match.TeamSeasonScoreVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.LeagueInfoExample;
import com.eastng.football.core.entity.match.LeagueSeason;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;
import com.eastng.football.core.entity.match.TeamSeasonScore;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.core.service.match.persistence.LeagueSeasonMapper;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.core.service.match.persistence.TeamSeasonScoreMapper;
import com.eastng.football.util.BeanUtils;
import com.github.pagehelper.PageHelper;

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
		if (leagueInfos != null && leagueInfos.size() > 0) {
			for (LeagueInfo leagueInfo : leagueInfos) {
				LeagueInfoVO infoVO = new LeagueInfoVO();
				BeanUtils.copyProperties(leagueInfo, infoVO);
				leagueInfoVOs.add(infoVO);
			}
		} else {
			throw new FootballWebException(ExceptionCode.MISS_LEAGUES);
		}
		return leagueInfoVOs;
	}

	@Override
	public Map<String, Object> findLeagueInfo(String leagueNo) throws FootballWebException {
		if (StringUtils.isEmpty(leagueNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "leagueNo is null");
		}
		LeagueSeason leagueSeason = findMaxLeagueSeasonBySeasonNo(leagueNo);
		SeasonVo seasonVo = new SeasonVo();
		BeanUtils.copyProperties(leagueSeason, seasonVo);
		String seasonNo = leagueSeason.getSeasonNo();
		List<Map<String, Object>> matchList = findMatchBySeasonNo(seasonNo, leagueNo);
		List<TeamSeasonScoreVO> teamSeasonScoreList = findTeamSeasonScoreBySeasonNo(seasonNo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("leagueSeason", seasonVo);
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
		if (StringUtils.isEmpty(seasonNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "seasonNo is null");
		}
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
	private List<Map<String, Object>> findMatchBySeasonNo(String seasonNo, String leagueNo)
			throws FootballWebException {
		if (StringUtils.isEmpty(leagueNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "leagueNo is null");
		}
		if (StringUtils.isEmpty(seasonNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "seasonNo is null");
		}
		Map<String, List<MatchVO>> resultMap = new HashMap<String, List<MatchVO>>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		StringBuilder dateBuilder = new StringBuilder(sdf.format(date)).append(" 00:00:00");
		Map<String, String> params = new HashMap<String, String>();
		params.put("startDate", dateBuilder.toString());
		params.put("seasonNo", seasonNo);
		List<Match> matchList = matchMapper.selectMatchByBeginDateAndSeasonNo(params);
		if (matchList == null || matchList.size() <= 0) {
			params.put("endDate", dateBuilder.toString());
			// 有这种可能性 表示 这个赛季还没有比赛数据或者结束了
			// 这个是desc 排序的数据 如果有需要 倒过来排序
			matchList = matchMapper.selectMatchByEndDateAndLeagueNo(params);
		}
		if (matchList != null) {
			List<MatchVO> matchVOList = null;
			for (Match match : matchList) {
				MatchVO matchVO = new MatchVO();
				BeanUtils.copyProperties(match, matchVO);
				String yeadMonthDay = getYearMonthDay(matchVO.getMatchTime());
				if (resultMap.get(yeadMonthDay) == null) {
					matchVOList = new ArrayList<MatchVO>();
				} else {
					matchVOList = resultMap.get(yeadMonthDay);
				}
				matchVOList.add(matchVO);
				resultMap.put(yeadMonthDay, matchVOList);
			}
			Set<String> keySet = new TreeSet<String>();
			keySet.addAll(resultMap.keySet());
			for (String key : keySet) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("date", key);
				map.put("matchs", resultMap.get(key));
				resultList.add(map);
			}
		}
		return resultList;
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
		if (StringUtils.isEmpty(leagueNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "leagueNo is null");
		}
		params.put("leagueNo", leagueNo);
		LeagueSeason leagueSeason = leagueSeasonMapper.selectMaxLeagueSeasonByLeagueNo(params);
		if (leagueSeason == null) {
			throw new FootballWebException(ExceptionCode.MISS_SEASON);
		}
		return leagueSeason;
	}

	@Override
	public Map<String, Object> findTeamMatchHistory(String matchNo) throws FootballWebException {
		// Match match = matchMapper.selectMatchByMatchNo(matchNo);
		MatchExample example = new MatchExample();
		MatchExample.Criteria criteria = example.createCriteria();
		criteria.andMatchNoEqualTo(matchNo);
		List<Match> matchs = matchMapper.selectByExample(example);
		if (matchs == null || matchs.size() <= 0) {
			throw new FootballWebException(ExceptionCode.MISS_MATCH);
		}
		Match match = matchs.get(0);

		MatchVO matchVO = new MatchVO();
		 BeanUtils.copyProperties(match, matchVO);
		String hostTeamNo = match.getHostTeamNo();
		String guestTeamNo = match.getGuestTeamNo();
		if (StringUtils.isEmpty(hostTeamNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "hostTeamNo is null");
		}
		if (StringUtils.isEmpty(guestTeamNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "guestTeamNo is null");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<MatchVO> hostRecentMacth = queryRecentMatch(hostTeamNo);
		List<MatchVO> guestRecentMatch = queryRecentMatch(guestTeamNo);
		List<MatchVO> historyMatch = queryMatchHistory(hostTeamNo, guestTeamNo);
		result.put("match", matchVO);
		result.put("hostRecentMacth", hostRecentMacth);
		result.put("guestRecentMatch", guestRecentMatch);
		result.put("historyMatch", historyMatch);
		return result;
	}

	/**
	 * 查询最近比赛记录
	 * 
	 * @param paramVO
	 * @return
	 * @throws FootBallBizException
	 */
	public List<MatchVO> queryRecentMatch(String hostTeamNo) throws FootballWebException {
		if (StringUtils.isEmpty(hostTeamNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "hostTeamNo is null");
		}
		Match record = new Match();
		record.setHostTeamNo(hostTeamNo);
		record.setMatchStatus(CommonConstant.MATCH_STATUS_END);
		PageHelper.startPage(1, 10);
		List<Match> list = this.matchMapper.queryRecentMatch(record);

		List<MatchVO> resultList = BeanUtils.copyList(list, MatchVO.class);
		return resultList;
	}

	/**
	 * 查询两队交战记录
	 * 
	 * @param hostTeamNo
	 * @param guestTeamNo
	 * @return
	 * @throws FootballWebException
	 */
	public List<MatchVO> queryMatchHistory(String hostTeamNo, String guestTeamNo) throws FootballWebException {
		if (StringUtils.isEmpty(hostTeamNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "hostTeamNo is null");
		}
		if (StringUtils.isEmpty(guestTeamNo)) {
			throw new FootballWebException(ExceptionCode.MISS_PARAM, "guestTeamNo is null");
		}
		Match record = new Match();
		record.setHostTeamNo(hostTeamNo);
		record.setGuestTeamNo(guestTeamNo);
		record.setMatchStatus(CommonConstant.MATCH_STATUS_END);

		PageHelper.startPage(1, 10);
		List<Match> list = this.matchMapper.queryMatchHistory(record);

		List<MatchVO> resultList = BeanUtils.copyList(list, MatchVO.class);

		return resultList;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	private String getYearMonthDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

}
