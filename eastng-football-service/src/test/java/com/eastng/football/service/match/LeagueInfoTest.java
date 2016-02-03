package com.eastng.football.service.match;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.vo.match.LeagueInfoVO;

public class LeagueInfoTest {

ClassPathXmlApplicationContext context = null;
	
	LeagueInfoService leagueInfoService = null;
	
	@Before
	public void init(){
		System.out.println("******************* Service Test Begin******************");

        String[] configFiles = new String[] { "classpath:spring/spring-context.xml" };
        context = new ClassPathXmlApplicationContext(configFiles);
        context.start();
        leagueInfoService= (LeagueInfoService) context.getBean("leagueInfoService");
	}
	
	//@Test
	public void saveLeagueInfo(){
		this.deleteAll();
		
		LeagueInfoVO paramVO = new LeagueInfoVO();
		paramVO.setLeagueNo("201601040001");
		paramVO.setLeagueName("英格兰足球超级联赛");
		paramVO.setLeagueShortName("英超");
		paramVO.setEventType("0");
		paramVO.setContinent("欧洲");
		paramVO.setCountry("英格兰");
		this.leagueInfoService.saveLeagueInfo(paramVO);
		
		paramVO.setLeagueNo("201601040002");
		paramVO.setLeagueName("西班牙足球甲级联赛");
		paramVO.setLeagueShortName("西甲");
		paramVO.setEventType("0");
		paramVO.setContinent("欧洲");
		paramVO.setCountry("西班牙");
		this.leagueInfoService.saveLeagueInfo(paramVO);
		
		paramVO.setLeagueNo("201601040003");
		paramVO.setLeagueName("法国足球甲级联赛");
		paramVO.setLeagueShortName("法甲");
		paramVO.setEventType("0");
		paramVO.setContinent("欧洲");
		paramVO.setCountry("法国");
		this.leagueInfoService.saveLeagueInfo(paramVO);
		
		paramVO.setLeagueNo("201601040004");
		paramVO.setLeagueName("德国足球甲级联赛");
		paramVO.setLeagueShortName("德甲");
		paramVO.setEventType("0");
		paramVO.setContinent("欧洲");
		paramVO.setCountry("德国");
		this.leagueInfoService.saveLeagueInfo(paramVO);
		
		paramVO.setLeagueNo("201601040005");
		paramVO.setLeagueName("意大利足球甲级联赛");
		paramVO.setLeagueShortName("意甲");
		paramVO.setEventType("0");
		paramVO.setContinent("欧洲");
		paramVO.setCountry("意大利");
		this.leagueInfoService.saveLeagueInfo(paramVO);
	}
	
	private void deleteAll(){
		int result = this.leagueInfoService.deleteAll();
		System.out.println("清空T_LEAGUE_INFO表，共"+result+"条数据。");
	}
	
	@org.junit.After
	public void end(){
		System.out.println("******************* Service Test end******************");
	}
}
