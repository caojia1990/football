package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.lottery.QueryOddsParamVO;
import com.eastng.football.web.view.easyui.DataGridResult;
import com.eastng.football.web.view.lottery.OddsResultVO;

@Controller
public class LotteryController {

	@Autowired
	private OddsService oddsService;
	
	@RequestMapping(value="queryOdds" ,method = RequestMethod.POST)
	@ResponseBody
	public DataGridResult<OddsResultVO> queryOddsInfoByMatchNo(@RequestParam(value="matchNo" ,required=false)String matchNo,
			@RequestParam(value="rows",required = false)int rows,
			@RequestParam(value="page",required = false)int page){
		
		QueryOddsParamVO paramVO = new QueryOddsParamVO();
		paramVO.setMatchNo(matchNo);
		paramVO.setPage(page);
		paramVO.setRows(rows);
		PageResult<OddsVO> pageResult = oddsService.queryOddsByMatchNo(paramVO);
		
		DataGridResult<OddsResultVO> result= new DataGridResult<OddsResultVO>();
		result.setTotal(pageResult.getTotal());
		
		//封装返回信息
		List<OddsResultVO> list = new ArrayList<OddsResultVO>();
		if(!StringUtils.isEmpty(pageResult)){
			for(OddsVO oddsVO:pageResult.getResult()){
				OddsResultVO oddsResultVO = new OddsResultVO();
				BeanUtils.copyProperties(oddsVO, oddsResultVO);
				list.add(oddsResultVO);
			}
		}
		result.setRows(list);
		return result;
	}
}
