package com.eastng.football.service.match;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;
import com.eastng.football.core.service.match.persistence.MatchMapper;

public class MatchTest {
	
	public static SqlSessionFactory sqlSessionFactory;

	@Before
	public void init(){
		String resource = "mybatis/mybatis-config.xml";
		if(sqlSessionFactory ==null){
			
			try {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void selectMatch(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MatchMapper matchMapper = session.getMapper(MatchMapper.class);
			Match match = matchMapper.selectMatchByMatchNo("123");
			System.out.println(match);
		} finally {
			session.close();
		}
	}
	
	@Test
	public void saveMatch(){
		SqlSession session = sqlSessionFactory.openSession();
		Match match = new Match();
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
		match.setLeagueNo("123");
		//赛季名称
		match.setSeasonName("15-16赛季");
		//比赛时间
		match.setMatchTime(new Date());
		
		try {
			MatchMapper matchDao = session.getMapper(MatchMapper.class);
			Integer result = matchDao.saveMatch(match);
			System.out.println(result);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void selectMatchByExample(){
		SqlSession session = sqlSessionFactory.openSession();
		MatchExample example = new MatchExample();
		example.setOrderByClause("ROUND");
		example.createCriteria().andLeagueNoEqualTo("123")
			.andSeasonNameEqualTo("1231");
		try {
			MatchMapper matchMapper = session.getMapper(MatchMapper.class);
			List<Match> list = matchMapper.selectByExample(example);
			matchMapper.deleteByExample(example);
			System.out.println(list);
			session.commit();
		} finally {
			session.close();
		}
		
	}
}
