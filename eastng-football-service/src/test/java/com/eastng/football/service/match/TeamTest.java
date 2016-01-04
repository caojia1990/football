package com.eastng.football.service.match;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.TeamVO;

public class TeamTest {
	
    ClassPathXmlApplicationContext context = null;
	
    TeamService teamService = null;

	@Before
	public  void setUp() throws Exception {
		System.out.println("******************* Service Test Begin******************");

        String[] configFiles = new String[] { "classpath:spring/spring-context.xml" };
        context = new ClassPathXmlApplicationContext(configFiles);
        context.start();
        teamService= (TeamService) context.getBean("teamService");
	}

	@After
	public  void tearDown() throws Exception {
		System.out.println("******************* Service Test End******************");
	}

	@Test
	public void test() {
		TeamVO paramVO = new TeamVO();
		paramVO.setTeamNo("YC0001");
		paramVO.setTeamName("曼彻斯特联");
		paramVO.setShortName("曼联");
		paramVO.setTeamNameEng("Manchester United");
		 /**球队类型  0：国家队；1：俱乐部 */
		paramVO.setTeamType("1");
		
		paramVO.setEstablishDate(new Date());
		
		paramVO.setContinent("欧洲");
		
		paramVO.setCountry("英格兰");
		
		this.teamService.saveTeam(paramVO);
	}

}
