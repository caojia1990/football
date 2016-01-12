package com.eastng.football.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.match.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping("queryMatchByMatchNo")
	@ResponseBody
	public MatchVO queryMatchByMatchNo(String matchNo){
		return this.matchService.queryMatchByMatchNo("123");
	}
	
	
	

}
