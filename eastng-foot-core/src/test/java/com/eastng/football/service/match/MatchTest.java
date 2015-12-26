package com.eastng.football.service.match;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.eastng.football.entity.match.Match;
import com.eastng.football.service.match.mapper.MatchMapper;

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
			MatchMapper matchDao = session.getMapper(MatchMapper.class);
			Match match = matchDao.selectMatch("123");
			System.out.println(match);
		} finally {
			session.close();
		}
		
	}
}
