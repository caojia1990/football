package com.eastng.football.core.service.match.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.entity.match.MatchExample;

/**
 * 比赛Mapper接口
 * @author caojia
 */
public interface MatchMapper {

    /**
     * 保存比赛信息，采用XML方式映射
     * @param match
     * @return 保存记录数
     */
    Integer saveMatch(Match match);
    
    /**
     * 根据条件查询赛程信息
     * @param match
     * @return 赛程信息列表
     */
    List<Match> queryMatchByCondition(Map<String,Object> map);
    
    /**
     * 根据条件查询赛程信息
     * @param match
     * @return 赛程信息列表
     */
    List<Match> selectByCondition(Match match);
    
    /**
     * 根据比赛编号查询比赛详细信息，采用注解方式映射
     * @param matchNo
     * @return
     */
    @Select("select * from t_match where match_no = #{matchNo}")
    Match selectMatchByMatchNo(String matchNo);
    
    /**
     * Mybatis-Generator生成的复杂查询方法
     * @param example 查询辅助类
     * @return 比赛列表
     */
    List<Match> selectByExample(MatchExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_match
     *
     * @mbggenerated Thu Jan 07 16:51:55 CST 2016
     */
    int deleteByExample(MatchExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_match
     *
     * @mbggenerated Thu Jan 07 16:51:55 CST 2016
     */
    int updateByExampleSelective(@Param("record") Match record, @Param("example") MatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_match
     *
     * @mbggenerated Thu Jan 07 16:51:55 CST 2016
     */
    int updateByExample(@Param("record") Match record, @Param("example") MatchExample example);
    
    int updateByPrimaryKeySelective(Match record);
    
    /**
     * 批量插入
     * @param records
     * @return
     */
    int batchInsert(List<Match> records);
    
    /**
     * 查询历史交战信息
     * @param record
     * @return
     */
    List<Match> queryMatchHistory(Match record);
    
    /**
     * 查询最近战况
     * @param record
     * @return
     */
    List<Match> queryRecentMatch(Match record);
    
    
    /**
     * 可根据所有条件查询比赛信息
     * @param record
     * @return
     */
    List<Match> queryMatchByAllCondition(Match record);
    
    /**
     * 查询主场已完成场次
     * @param seasonNo
     * @param teamNo
     * @param round
     * @return
     */
    @Select("select count(1) as hostPlay,sum(HOST_GOAL) as hostGoals,sum(GUEST_GOAL) as guestGoals "
            + "from t_match where season_no = #{0} and host_team_no = #{1} and round <= #{2} and match_status = '2'")
    Map<String,Object> selectCountByHostTeamAndRound(String seasonNo, String teamNo, Integer round);
    
    /**
     * 查询客场已完成场次
     * @param seasonNo
     * @param teamNo
     * @param round
     * @return
     */
    @Select("select count(1) as guestPlay,sum(HOST_GOAL) as hostGoals,sum(GUEST_GOAL) as guestGoals "
            + "from t_match where season_no = #{0} and guest_team_no = #{1} and round <= #{2} and match_status = '2'")
    Map<String,Object> selectCountByGuestTeamAndRound(String seasonNo, String teamNo, Integer round);
    
    /**
     * 查询主场胜平负累计
     * @param seasonNo
     * @param teamNo
     * @param round
     * @return
     */
    @Select("select case when t.HOST_GOAL>t.GUEST_GOAL then '3' when t.HOST_GOAL = t.GUEST_GOAL then '1' when t.HOST_GOAL < t.GUEST_GOAL then '0' else '9' end as result,count(1) as sum "
            + "from t_match t where season_no = #{0} and host_team_no = #{1} and round <= #{2} and t.match_status = '2'"
            + "group by case when t.HOST_GOAL>t.GUEST_GOAL then '3' when t.HOST_GOAL = t.GUEST_GOAL then '1' when t.HOST_GOAL < t.GUEST_GOAL then '0' else '9' end")
    List<Map<String, Object>> selectHostResult(String seasonNo, String teamNo, Integer round);
    
    /**
     * 查询客场胜平负累计
     * @param seasonNo
     * @param teamNo
     * @param round
     * @return
     */
    @Select("select case when t.HOST_GOAL>t.GUEST_GOAL then '3' when t.HOST_GOAL = t.GUEST_GOAL then '1' when t.HOST_GOAL < t.GUEST_GOAL then '0' else '9' end as result,count(1) as sum "
            + "from t_match t where season_no = #{0} and guest_team_no = #{1} and round <= #{2} and t.match_status = '2'"
            + "group by case when t.HOST_GOAL>t.GUEST_GOAL then '3' when t.HOST_GOAL = t.GUEST_GOAL then '1' when t.HOST_GOAL < t.GUEST_GOAL then '0' else '9' end")
    List<Map<String, Object>> selectGuestResult(String seasonNo, String teamNo, Integer round);
}
