package com.eastng.football.service.match.dao;

import org.apache.ibatis.annotations.Select;

import com.eastng.football.entity.match.Match;

/**
 * 比赛Dao接口
 * @author caojia
 */
public interface MatchDao {

	@Select("select * from t_match where id = #{matchNo}")
	Match selectMatch(String matchNo);
}
