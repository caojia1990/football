package com.eastng.football.service.match.mapper;

import org.apache.ibatis.annotations.Select;

import com.eastng.football.entity.match.Match;

/**
 * 比赛Mapper接口
 * @author caojia
 */
public interface MatchMapper {

	Integer savaMatch(Match match);
	
	@Select("select * from t_match where id = #{matchNo}")
	Match selectMatch(String matchNo);
}
