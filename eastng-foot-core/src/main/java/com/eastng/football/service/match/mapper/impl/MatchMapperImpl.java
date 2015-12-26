package com.eastng.football.service.match.mapper.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.eastng.football.entity.match.Match;
import com.eastng.football.service.match.mapper.MatchMapper;

/**
 * 比赛数据操作实现类
 * @author caojia
 */
public class MatchMapperImpl implements MatchMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public Integer savaMatch(Match match) {
		this.sqlSession.insert("MatchDaoImpl.saveMatch", match);
		return null;
	}
	
	/**
	 * 根据比赛编号查询比赛信息
	 */
	public Match selectMatch(String matchNo) {
		MatchMapper matchDao = sqlSession.getMapper(MatchMapper.class);
		Match match = matchDao.selectMatch(matchNo);
		return match;
	}


}
