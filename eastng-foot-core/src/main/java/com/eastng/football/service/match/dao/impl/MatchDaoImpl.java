package com.eastng.football.service.match.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.eastng.football.entity.match.Match;
import com.eastng.football.service.match.dao.MatchDao;

/**
 * 比赛数据操作实现类
 * @author caojia
 */
public class MatchDaoImpl implements MatchDao {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 根据比赛编号查询比赛信息
	 */
	public Match selectMatch(String matchNo) {
		MatchDao matchDao = sqlSession.getMapper(MatchDao.class);
		Match match = matchDao.selectMatch(matchNo);
		return match;
	}

}
