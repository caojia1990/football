package com.eastng.football.core.service.match.persistence;

import com.eastng.football.core.entity.match.Team;
import com.eastng.football.core.entity.match.TeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int countByExample(TeamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int deleteByExample(TeamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int insert(Team record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int insertSelective(Team record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    List<Team> selectByExample(TeamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    Team selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int updateByExampleSelective(@Param("record") Team record, @Param("example") TeamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int updateByExample(@Param("record") Team record, @Param("example") TeamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int updateByPrimaryKeySelective(Team record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_team
     *
     * @mbggenerated Sun Jan 03 13:46:51 CST 2016
     */
    int updateByPrimaryKey(Team record);
    
    /**
     * 批量插入球队记录
     * @param list
     * @return
     */
    int batchInsert(List<Team> list);
    
    /**
     * 查询赛季所有球队信息
     * @param seasonNo 赛季编号
     * @return
     */
    @Select("select t_team.team_no as teamNo,t_team.short_name as shortName from t_team ,t_team_season  where t_team.team_no = t_team_season.team_no and t_team_season.season_no = #{0}")
    List<Team> selectBySeasonNo(String seasonNo);
    
}