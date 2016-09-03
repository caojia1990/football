package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.TeamVO;
import com.eastng.football.core.entity.match.Team;
import com.eastng.football.core.entity.match.TeamExample;
import com.eastng.football.core.entity.team.TeamSeason;
import com.eastng.football.core.service.match.persistence.TeamMapper;
import com.eastng.football.core.service.team.persistence.TeamSeasonMapper;
import com.eastng.football.util.BeanUtils;
import com.eastng.football.util.GenerateCodeUtil;

/**
 * 球队信息服务实现类
 * @author caojia
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService {
    
    static Logger logger = Logger.getLogger(TeamServiceImpl.class);
    
    @Autowired
    private TeamMapper teamMapper;
    
    @Autowired
    private TeamSeasonMapper teamSeasonMapper;
    
    public String saveTeam(TeamVO paramVO) {
        
        //先查询是否已经存在该球队
        TeamVO team = this.queryTeamByName(paramVO.getShortName());
        String teamNo = team.getTeamNo();
        if(StringUtils.isEmpty(teamNo)){
            Team record = new Team();
            BeanUtils.copyProperties(paramVO, record);
            record.setTeamNo(GenerateCodeUtil.generateTeamNo());
            this.teamMapper.insertSelective(record);
            teamNo = record.getTeamNo();
        }
        
        //查询在赛季球队表中是否已经注册该球队
        String seasonNo = paramVO.getSeasonNo();
        if(!StringUtils.isEmpty(seasonNo)){
            //查询球队是否已注册到该赛季
            TeamSeason teamSeason = this.teamSeasonMapper.selectBySeasonNoAndTeamNo(seasonNo, teamNo);
            if(teamSeason == null){
                TeamSeason record = new TeamSeason();
                record.setSeasonNo(seasonNo);
                record.setTeamNo(teamNo);
                record.setTeamName(paramVO.getShortName());
                this.teamSeasonMapper.insertSelective(record);
                logger.info("球队注册成功，赛季编号：" + seasonNo);
            }
        }
        
        return teamNo;
    }

    /**
     * 保存球队信息列表
     */
    public Integer saveTeams(List<TeamVO> list) {
        List<Team> teams = new ArrayList<Team>();
        for(TeamVO teamVO : list){
            Team record = new Team();
            BeanUtils.copyProperties(teamVO, record);
            record.setTeamNo(GenerateCodeUtil.generateTeamNo());
            teams.add(record);
        }
        int i = this.teamMapper.batchInsert(teams);
        return i;
    }

    /**
     * 根据球队简称查询球队信息
     */
    public TeamVO queryTeamByName(String teamName) {
        TeamExample example = new TeamExample();
        example.createCriteria().andShortNameEqualTo(teamName);
        List<Team> list = this.teamMapper.selectByExample(example);
        TeamVO teamVO = new TeamVO();
        if(list!=null && list.size()>0){
            BeanUtils.copyProperties(list.get(0), teamVO);
        }
        logger.info("根据球队简称查询球队信息返回："+ToStringBuilder.reflectionToString(teamVO, ToStringStyle.MULTI_LINE_STYLE));
        return teamVO;
    }

}
