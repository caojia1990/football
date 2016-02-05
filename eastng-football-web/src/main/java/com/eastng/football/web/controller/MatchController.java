package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.web.view.Tree;
import com.eastng.football.web.view.TreeAttributes;

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
	public List<MatchVO> queryMatchByDate(Date date){
		QueryMatchParamVO paramVO = new QueryMatchParamVO();
		paramVO.setBeginDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		paramVO.setEndDate(cal.getTime());
		return this.matchService.queryMatchSchedule(paramVO);
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
	@RequestMapping("queryMatch")
	@ResponseBody
	public List<MatchVO> queryMatch(@RequestParam(value="paramVO",required = false)QueryMatchParamVO paramVO){
		if(paramVO == null){
			
		}
		return this.matchService.queryMatchSchedule(paramVO);
		
	}
}
