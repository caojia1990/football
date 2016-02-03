package com.eastng.football.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.api.vo.match.TeamVO;
import com.eastng.football.web.view.Tree;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
    public List<Tree> queryDistrictByPid(@RequestParam(value="id",required = false)String pid ,HttpServletRequest request){
    	List<DistrictVO> list = this.districtService.queryDistrictByPid(pid);
    	List<Tree> trees = new ArrayList<Tree>();
    	for(DistrictVO districtVO:list){
    		Tree tree = new Tree();
    		tree.setId(districtVO.getDistrictNo());
    		tree.setText(districtVO.getDistrictName());
    		tree.setState("closed");
    		trees.add(tree);
    	}
    	//如果地区没有查到就查联赛信息
    	if(trees.size()<1){
    		List<LeagueInfoVO> infoVOs = new ArrayList<LeagueInfoVO>();
    		LeagueInfoVO infoVO = new LeagueInfoVO();
    		infoVO.setCountry(pid);
    		infoVOs = this.leagueInfoService.queryLeagueInfoByCondition(infoVO);
    		for(LeagueInfoVO vo:infoVOs){
    			Tree tree = new Tree();
    			tree.setId(vo.getLeagueNo());
    			tree.setText(vo.getLeagueShortName());
    			trees.add(tree);
    		}
    	}
    	
    	return trees;
    }
	
}
