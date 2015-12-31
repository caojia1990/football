package com.eastng.football.service.match;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eastng.football.service.api.match.MatchService;
import com.eastng.football.service.vo.MatchVO;
import com.eastng.football.service.vo.QueryMatchParamVO;


public class MatchTest {

	ClassPathXmlApplicationContext context = null;
	
	MatchService matchService = null;
	@Before
	public void init(){
		System.out.println("******************* Service Test Begin******************");

        String[] configFiles = new String[] { "classpath:spring/spring-context.xml" };
        context = new ClassPathXmlApplicationContext(configFiles);
        context.start();
        matchService= (MatchService) context.getBean("matchService");
	}
	
	@Test
	public void test() {
		MatchVO match = new MatchVO();
		//比赛编号
		match.setMatchNo("201512261159001");
		//主队球队编号
		match.setHostTeamNo("001");
		//主队简称
		match.setHostShortName("曼联");
		//主队进球
		match.setHostGoal(1);
		//主队半场进球
		match.setHalfTimeHostGoal(0);
		//客队球队编号
		match.setGuestTeamNo("002");
		//客队简称
		match.setGuestShortName("沃特福德");
		//客队进球
		match.setGuestGoal(2);
		//客队半场进球
		match.setHalfTimeGuestGoal(1);
		//比赛状态
		match.setMatchStatus("1");
		//比赛类型
		match.setEventId(1);
		//赛季名称
		match.setSeasonName("15-16赛季");
		//比赛时间
		match.setMatchTime(new Date());
		this.matchService.saveMatch(match);
	}
	
	@Test
	public void queryMatchTest(){
		QueryMatchParamVO paramVO = new QueryMatchParamVO();
		paramVO.setGuestTeamNo("002");
		this.matchService.queryMatchSchedule(paramVO);
	}

}
