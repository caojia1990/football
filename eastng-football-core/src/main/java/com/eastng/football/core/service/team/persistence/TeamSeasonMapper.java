package com.eastng.football.core.service.team.persistence;

import org.apache.ibatis.annotations.Select;

import com.eastng.football.core.entity.team.TeamSeason;

public interface TeamSeasonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    int insert(TeamSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    int insertSelective(TeamSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    TeamSeason selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    int updateByPrimaryKeySelective(TeamSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team_season
     *
     * @mbggenerated Fri Aug 26 15:31:36 CST 2016
     */
    int updateByPrimaryKey(TeamSeason record);
    
    @Select("select * from t_team_season where season_no = #{0} and team_no = #{1}")
    TeamSeason selectBySeasonNoAndTeamNo(String seasonNo, String teamNo);
    
}