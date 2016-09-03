package com.eastng.football.service.team;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.eastng.football.core.entity.team.TeamSeason;
import com.eastng.football.core.service.team.persistence.TeamSeasonMapper;

public class TeamTest {

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
    public void selectTeamSeason(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeamSeasonMapper teamSeasonMapper = session.getMapper(TeamSeasonMapper.class);
            TeamSeason ts = teamSeasonMapper.selectBySeasonNoAndTeamNo("001005001", "123");
            System.out.println(ts);
        } finally {
            session.close();
        }
    }
    
    @Test
    public void saveTeamSeason(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeamSeasonMapper teamSeasonMapper = session.getMapper(TeamSeasonMapper.class);
            TeamSeason record = new TeamSeason();
            record.setSeasonNo("001005001");
            record.setTeamNo("123");
            
            teamSeasonMapper.insertSelective(record);
            
            TeamSeason ts = teamSeasonMapper.selectBySeasonNoAndTeamNo("001005001", "123");
            System.out.println(ts);
        } finally {
            session.close();
        }
    }
}
