package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.TeamVO;
import com.eastng.football.core.entity.match.Team;
import com.eastng.football.core.entity.match.TeamExample;
import com.eastng.football.core.service.match.persistence.TeamMapper;
import com.eastng.football.util.BeanUtils;
import com.eastng.football.util.GenerateCodeUtil;

/**
 * 球队信息服务实现类
 * @author caojia
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamMapper teamMapper;
	
	public Integer saveTeam(TeamVO paramVO) {
		
		Team record = new Team();
		BeanUtils.copyProperties(paramVO, record);
		record.setTeamNo(GenerateCodeUtil.generateTeamNo());
		Integer result = this.teamMapper.insertSelective(record);
		return result;
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
		Team record = this.teamMapper.selectByExample(example).get(0);
		TeamVO teamVO = new TeamVO();
		BeanUtils.copyProperties(record, teamVO);
		return teamVO;
	}

}
