package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eastng.common.echarts.Option;
import com.eastng.common.echarts.Option.Legend;
import com.eastng.common.echarts.Option.Series;
import com.eastng.common.echarts.Option.Series.Data;
import com.eastng.common.echarts.Option.Title;
import com.eastng.common.echarts.Option.Tooltip;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.web.view.common.Response;
import com.eastng.football.web.view.match.QueryRecentMatchParamVO;
import com.eastng.football.web.view.match.WqueryRecentMatchChartParamVO;
import com.eastng.football.web.view.match.WqueryRecentMatchChartResultVO;

@Controller
public class EchartsController {

	static Logger logger = Logger.getLogger(EchartsController.class);
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping(value="getEchartsPie",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
    public String queryRecentMatch(QueryRecentMatchParamVO paramVO) throws FootBallBizException{
		
		logger.info("查询球队近况WEB层入参"+JSON.toJSONString(paramVO));
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
		int win = 0;
		int draw = 0;
		int lose = 0;
		List<MatchVO> list = pageResult.getResult();
		if(list != null){
			for(MatchVO matchVO:list){
				//主队
				if(matchVO.getHostTeamNo().equals(paramVO.getTeamNo())){
					if(matchVO.getHostGoal()>matchVO.getGuestGoal()){
						win +=1;
					}else if(matchVO.getHostGoal()<matchVO.getGuestGoal()){
						lose +=1;
					}else {
						draw +=1;
					}
				}else {
					//客队
					if(matchVO.getHostGoal()>matchVO.getGuestGoal()){
						lose +=1;
					}else if(matchVO.getHostGoal()<matchVO.getGuestGoal()){
						win +=1;
					}else {
						draw +=1;
					}
				}
			}
		}
		Option option = new Option();
		Title title = option.createTitle();
		//标题
		title.setText("近"+paramVO.getRows()+"场交锋");
		//标题居中
		title.setX("center");
		Tooltip tooltip = option.createTooltip();
		tooltip.setTrigger("item");
		tooltip.setFormatter("{a} <br/>{b} : {c} ({d}%)");
		
		Legend legend = option.createLegend();
		legend.setOrient("vertical");
		legend.setLeft("left");
		List<String> data = new ArrayList<String>();
		data.add("胜");
		data.add("平");
		data.add("负");
		legend.setData(data);
		
		Series series = new Series();
		series.setName("胜平负比");
		series.setType("pie");
		series.setRadius("55%");
		Data data2 = new Data();
		data2.setName("胜");
		data2.setValue(win);
		series.addData(data2);
		Data data3 = new Data();
		data3.setName("平");
		data3.setValue(draw);
		series.addData(data3);
		Data data4 = new Data();
		data4.setName("负");
		data4.setValue(lose);
		series.addData(data4);
		option.addSeries(series);
		String opString = JSON.toJSONString(option, true);
		logger.info("转换Echarts option为字符串："+opString);
		return opString;
	}
	
	@RequestMapping(value="queryRecentMatchChart")
	@ResponseBody
	public Response<WqueryRecentMatchChartResultVO> queryRecentMatchChart(WqueryRecentMatchChartParamVO paramVO) throws FootBallBizException{
		Response<WqueryRecentMatchChartResultVO> response = new Response<>();
		QueryMatchParamVO innerparamVO = new QueryMatchParamVO();
		//球队编号
		innerparamVO.setHostTeamNo(paramVO.getTeamNo());
		//赛季编号
		innerparamVO.setSeasonNo(paramVO.getSeasonNo());
		//比赛时间
		innerparamVO.setBeginDate(paramVO.getMatchDate());
		innerparamVO.setPage(1);
		innerparamVO.setRows(10);
		PageResult<MatchVO> pageResult = this.matchService.queryRecentMatch(innerparamVO);
		int win = 0;
		int draw = 0;
		int lose = 0;
		List<MatchVO> list = pageResult.getResult();
		if(list != null){
			for(MatchVO matchVO:list){
				//主队
				if(matchVO.getHostTeamNo().equals(paramVO.getTeamNo())){
					if(matchVO.getHostGoal()>matchVO.getGuestGoal()){
						win +=1;
					}else if(matchVO.getHostGoal()<matchVO.getGuestGoal()){
						lose +=1;
					}else {
						draw +=1;
					}
				}else {
					//客队
					if(matchVO.getHostGoal()>matchVO.getGuestGoal()){
						lose +=1;
					}else if(matchVO.getHostGoal()<matchVO.getGuestGoal()){
						win +=1;
					}else {
						draw +=1;
					}
				}
			}
		}
		WqueryRecentMatchChartResultVO responseBody = new WqueryRecentMatchChartResultVO();
		responseBody.setWin(win);
		responseBody.setDraw(draw);
		responseBody.setLose(lose);
		response.setResponseBody(responseBody);
		
		return response;
	}
}
