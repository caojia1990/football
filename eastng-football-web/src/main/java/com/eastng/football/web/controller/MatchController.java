package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
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
import com.eastng.football.web.view.match.QueryMatchResultVO;

@Controller
public class MatchController {
	
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
	public DataGridResult<MatchVO> queryMatchByDate(Date date){
		QueryMatchParamVO paramVO = new QueryMatchParamVO();
		paramVO.setBeginDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		paramVO.setEndDate(cal.getTime());
		PageResult<MatchVO> pageResult = this.matchService.queryMatchSchedule(paramVO);
		DataGridResult<MatchVO> result = new DataGridResult<MatchVO>();
		result.setTotal(pageResult.getTotal());
		result.setRows(pageResult.getResult());
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
	public DataGridResult<QueryMatchResultVO> queryMatch(@RequestParam(value="paramVO" ,required=false) String paramVO,
			@RequestParam(value="rows",required = false)int rows,
			@RequestParam(value="page",required = false)int page){
		
		QueryMatchParamVO innerparamVO = JSON.parseObject(paramVO, QueryMatchParamVO.class);
		if(innerparamVO == null){
			innerparamVO = new QueryMatchParamVO();
		}
		innerparamVO.setRows(rows);
		innerparamVO.setPage(page);
		PageResult<MatchVO> pageResult = this.matchService.queryMatchSchedule(innerparamVO);
		DataGridResult<QueryMatchResultVO> result = new DataGridResult<QueryMatchResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<QueryMatchResultVO> list = new ArrayList<QueryMatchResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(MatchVO matchVO:pageResult.getResult()){
				QueryMatchResultVO matchResultVO = new QueryMatchResultVO();
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
