package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.web.view.easyui.DataGridResult;
import com.eastng.football.web.view.easyui.Tree;
import com.eastng.football.web.view.easyui.TreeAttributes;
import com.eastng.football.web.view.match.QueryMatchHistoryParamVO;
import com.eastng.football.web.view.match.QueryRecentMatchParamVO;
import com.eastng.football.web.view.match.WqueryMatchParamVO;
import com.eastng.football.web.view.match.WqueryMatchResultVO;

@Controller
public class MatchController {
	
	static Logger logger = Logger.getLogger(MatchController.class);
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private LeagueInfoService leagueInfoService; 
	
	@RequestMapping("queryMatchByMatchNo")
	@ResponseBody
	public MatchVO queryMatchByMatchNo(String matchNo){
		return this.matchService.queryMatchByMatchNo("123");
	}
	
	/**
	 * 根据日期查询比赛信息
	 * @param date 
	 * @return
	 */
	@RequestMapping("queryMatchByDate")
	@ResponseBody
	public DataGridResult<WqueryMatchResultVO> queryMatchByDate(WqueryMatchParamVO paramVO){
		//QueryMatchParamVO innerparamVO = JSON.parseObject(paramStr, QueryMatchParamVO.class);
		Calendar cal = Calendar.getInstance();
		cal.setTime(paramVO.getMatchDate());
		cal.add(Calendar.DATE, 1);
		QueryMatchParamVO innerparamVO = new QueryMatchParamVO();
		innerparamVO.setBeginDate(paramVO.getMatchDate());
		innerparamVO.setEndDate(cal.getTime());
		PageResult<MatchVO> pageResult = this.matchService.queryMatchSchedule(innerparamVO);
		
		DataGridResult<WqueryMatchResultVO> result = new DataGridResult<WqueryMatchResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<WqueryMatchResultVO> list = new ArrayList<WqueryMatchResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(MatchVO matchVO:pageResult.getResult()){
				WqueryMatchResultVO matchResultVO = new WqueryMatchResultVO();
				BeanUtils.copyProperties(matchVO, matchResultVO);
				if(!StringUtils.isEmpty(matchVO.getHostGoal())&&!StringUtils.isEmpty(matchVO.getGuestGoal())){
					matchResultVO.setScore(matchVO.getHostGoal()+":"+matchVO.getGuestGoal());
				}
				list.add(matchResultVO);
			}
		}
		
		result.setRows(list);
		
		return result;
	}
	
	@RequestMapping("queryDistrictByPid")
	@ResponseBody
    public List<Tree<TreeAttributes>> queryDistrictByPid(@RequestParam(value="id",required = false)String pid ,HttpServletRequest request){
    	List<DistrictVO> list = this.districtService.queryDistrictByPid(pid);
    	List<Tree<TreeAttributes>> trees = new ArrayList<Tree<TreeAttributes>>();
    	for(DistrictVO districtVO:list){
    		Tree<TreeAttributes> tree = new Tree<TreeAttributes>();
    		TreeAttributes attributes = new TreeAttributes();
    		attributes.setLevel("1");
    		tree.setId(districtVO.getDistrictNo());
    		tree.setText(districtVO.getDistrictName());
    		tree.setState("closed");
    		tree.setAttributes(attributes);
    		trees.add(tree);
    	}
    	//如果地区没有查到就查联赛信息
    	if(trees.size()<1){
    		List<LeagueInfoVO> infoVOs = new ArrayList<LeagueInfoVO>();
    		LeagueInfoVO infoVO = new LeagueInfoVO();
    		infoVO.setCountry(pid);
    		infoVOs = this.leagueInfoService.queryLeagueInfoByCondition(infoVO);
    		for(LeagueInfoVO vo:infoVOs){
    			Tree<TreeAttributes> tree = new Tree<TreeAttributes>();
    			TreeAttributes attributes = new TreeAttributes();
        		attributes.setLevel("2");
    			tree.setId(vo.getLeagueNo());
    			tree.setText(vo.getLeagueShortName());
    			tree.setAttributes(attributes);
    			trees.add(tree);
    		}
    	}
    	
    	return trees;
    }
	
	/**
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="queryMatch" ,method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<WqueryMatchResultVO> queryMatch(QueryMatchParamVO innerparamVO){
		
		//QueryMatchParamVO innerparamVO = JSON.parseObject(paramVO, QueryMatchParamVO.class);
		if(innerparamVO == null){
			innerparamVO = new QueryMatchParamVO();
		}
		PageResult<MatchVO> pageResult = this.matchService.queryMatchSchedule(innerparamVO);
		DataGridResult<WqueryMatchResultVO> result = new DataGridResult<WqueryMatchResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<WqueryMatchResultVO> list = new ArrayList<WqueryMatchResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(MatchVO matchVO:pageResult.getResult()){
				WqueryMatchResultVO matchResultVO = new WqueryMatchResultVO();
				BeanUtils.copyProperties(matchVO, matchResultVO);
				if(!StringUtils.isEmpty(matchVO.getHostGoal())&&!StringUtils.isEmpty(matchVO.getGuestGoal())){
					matchResultVO.setScore(matchVO.getHostGoal()+":"+matchVO.getGuestGoal());
				}
				list.add(matchResultVO);
			}
		}
		
		result.setRows(list);
		return result;
	}
	
	/**
	 * 查询历史交战记录
	 * @param paramVO
	 * @return
	 * @throws FootBallBizException
	 */
	@RequestMapping(value="queryMatchHistory" ,method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<WqueryMatchResultVO> queryMatchHistory(QueryMatchHistoryParamVO paramVO) throws FootBallBizException{
		
		logger.info("查询历史对战记录入参"+ToStringBuilder.reflectionToString(paramVO, ToStringStyle.MULTI_LINE_STYLE));
		//QueryMatchParamVO innerparamVO = JSON.parseObject(paramVO, QueryMatchParamVO.class);
		if(paramVO == null){
			paramVO = new QueryMatchHistoryParamVO();
		}
		QueryMatchParamVO innerparamVO = new QueryMatchParamVO();
		BeanUtils.copyProperties(paramVO, innerparamVO);
		
		PageResult<MatchVO> pageResult = this.matchService.queryMatchHistory(innerparamVO);
		DataGridResult<WqueryMatchResultVO> result = new DataGridResult<WqueryMatchResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<WqueryMatchResultVO> list = new ArrayList<WqueryMatchResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(MatchVO matchVO:pageResult.getResult()){
				WqueryMatchResultVO matchResultVO = new WqueryMatchResultVO();
				BeanUtils.copyProperties(matchVO, matchResultVO);
				if(!StringUtils.isEmpty(matchVO.getHostGoal())&&!StringUtils.isEmpty(matchVO.getGuestGoal())){
					matchResultVO.setScore(matchVO.getHostGoal()+":"+matchVO.getGuestGoal());
				}
				list.add(matchResultVO);
			}
		}
		
		result.setRows(list);
		return result;
	}
	
	/**
	 * 查询近期战况
	 * @param paramVO
	 * @return
	 * @throws FootBallBizException
	 */
	@RequestMapping(value="queryRecentMatch" ,method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<WqueryMatchResultVO> queryRecentMatch(QueryRecentMatchParamVO paramVO) throws FootBallBizException{
		
		logger.info("查询球队近况WEB层入参"+ToStringBuilder.reflectionToString(paramVO, ToStringStyle.MULTI_LINE_STYLE));
		//QueryMatchParamVO innerparamVO = JSON.parseObject(paramVO, QueryMatchParamVO.class);
		if(paramVO == null){
			paramVO = new QueryRecentMatchParamVO();
		}
		QueryMatchParamVO innerparamVO = new QueryMatchParamVO();
		//球队编号
		innerparamVO.setHostTeamNo(paramVO.getTeamNo());
		//联赛编号
		innerparamVO.setLeagueNo(paramVO.getLeagueNo());
		//赛季名称
		innerparamVO.setSeasonName(paramVO.getSeasonName());
		//比赛时间
		innerparamVO.setBeginDate(paramVO.getMatchTime());
		//
		innerparamVO.setPage(paramVO.getPage());
		innerparamVO.setRows(paramVO.getRows());
		
		PageResult<MatchVO> pageResult = this.matchService.queryRecentMatch(innerparamVO);
		DataGridResult<WqueryMatchResultVO> result = new DataGridResult<WqueryMatchResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<WqueryMatchResultVO> list = new ArrayList<WqueryMatchResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(MatchVO matchVO:pageResult.getResult()){
				WqueryMatchResultVO matchResultVO = new WqueryMatchResultVO();
				BeanUtils.copyProperties(matchVO, matchResultVO);
				if(!StringUtils.isEmpty(matchVO.getHostGoal())&&!StringUtils.isEmpty(matchVO.getGuestGoal())){
					matchResultVO.setScore(matchVO.getHostGoal()+":"+matchVO.getGuestGoal());
				}
				list.add(matchResultVO);
			}
		}
		
		result.setRows(list);
		return result;
	}
	
	
}
