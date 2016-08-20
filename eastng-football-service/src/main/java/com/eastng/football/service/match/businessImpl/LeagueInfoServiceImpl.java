package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eastng.football.api.service.match.LeagueInfoService;
import com.eastng.football.api.vo.match.LeagueInfoVO;
import com.eastng.football.core.entity.match.LeagueInfo;
import com.eastng.football.core.entity.match.LeagueInfoExample;
import com.eastng.football.core.entity.match.LeagueInfoExample.Criteria;
import com.eastng.football.core.service.match.persistence.LeagueInfoMapper;
import com.eastng.football.util.BeanUtils;

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

	/**
	 * 根据条件查询联赛信息
	 * @param leagueInfoVO
	 * @return
	 */
	public List<LeagueInfoVO> queryLeagueInfoByCondition(LeagueInfoVO leagueInfoVO) {
		LeagueInfoExample example = new LeagueInfoExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(leagueInfoVO.getCountry())){
			criteria.andCountryEqualTo(leagueInfoVO.getCountry());
		}
		List<LeagueInfo> records = this.leagueInfoMapper.selectByExample(example);
		List<LeagueInfoVO> list= new ArrayList<LeagueInfoVO>();
		for(LeagueInfo record:records){
			LeagueInfoVO infoVO = new LeagueInfoVO();
			BeanUtils.copyProperties(record, infoVO);
			list.add(infoVO);
		}
		return list;
	}

	/**
	 * 查询联赛信息
	 */
    public LeagueInfoVO queryLeagueInfoByLeagueNo(String leagueNo) {
        LeagueInfo record = this.leagueInfoMapper.selectByLeagueNo(leagueNo);
        LeagueInfoVO leagueInfoVO = new LeagueInfoVO();
        BeanUtils.copyProperties(record, leagueInfoVO);
        return leagueInfoVO;
    }

}
