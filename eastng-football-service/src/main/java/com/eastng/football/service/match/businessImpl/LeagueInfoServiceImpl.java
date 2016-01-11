package com.eastng.football.service.match.businessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;

@Service("leagueInfoService")
public class LeagueInfoServiceImpl implements LeagueInfoService{

	@Autowired
	private LeagueInfoMapper leagueInfoMapper;
	
	/**
	 * 保存联赛信息
	 * @param paramVO 联赛信息VO
	 * @return
	 */
	public Integer saveLeagueInfo(LeagueInfoVO paramVO) {

		LeagueInfo record = new LeagueInfo();
		record.setLeagueNo(paramVO.getLeagueNo());
		record.setLeagueName(paramVO.getLeagueName());
		record.setLeagueShortName(paramVO.getLeagueShortName());
		record.setEventType(paramVO.getEventType());
		record.setContinent(paramVO.getContinent());
		record.setCountry(paramVO.getCountry());
		Integer result = this.leagueInfoMapper.insert(record);
		return result;
	}

	/**
	 * 删除所有数据
	 */
	public Integer deleteAll() {
		Integer result = this.leagueInfoMapper.deleteAll();
		return result;
	}

}
