package com.eastng.football.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.exception.ExceptionCode;
import com.eastng.football.api.exception.FootballWebException;
import com.eastng.football.api.service.match.WebLeaguesService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.web.controller.bean.JsonResponse;

/**
 * 
 * @author laughing
 * @date 2016-09-25 21:33:15
 *
 */
@Controller
@RequestMapping(value = "web/m/")
public class WebMatchController {

	@Autowired
	private WebLeaguesService webLeaguesService;

	@RequestMapping(value = "findAllLeagues")
	@ResponseBody
	public JsonResponse<List<LeagueInfoVO>> findAllLeagues() throws FootballWebException {
		JsonResponse<List<LeagueInfoVO>> jsonResponse = new JsonResponse<List<LeagueInfoVO>>();
		List<LeagueInfoVO> infoVOList = webLeaguesService.findAllLeagueInfo();
		jsonResponse.setResponseBody(infoVOList);
		return jsonResponse;
	}

	/**
	 * 根据时间查询前后3天的比赛
	 * 
	 * @param leagueNo
	 * @return
	 * @throws FootballWebException
	 */
	@RequestMapping(value = "findLeaguesByleagueNo")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JsonResponse findLeaguesByleagueNo(HttpServletRequest request) throws FootballWebException {
		String leagueNo = request.getParameter("leagueNo");
		if(null == leagueNo || leagueNo.trim().equals("")){
			throw new FootballWebException(ExceptionCode.MISS_PARAM , "leagueNo is  null");
		}
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> result = webLeaguesService.findLeagueInfo(leagueNo);
		jsonResponse.setResponseBody(result);
		return jsonResponse;
	}
}
