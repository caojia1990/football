package com.eastng.football.service.lottery.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.lottery.SaveOddsResultVO;
import com.eastng.football.core.entity.lottery.Odds;
import com.eastng.football.core.service.lottery.persistence.OddsMapper;
import com.eastng.football.util.BeanUtils;

public class OddsServiceImpl implements OddsService {

	@Autowired
	private OddsMapper oddsMapper;
	
	/**
	 * 保存赔率信息
	 * @param oddsVO
	 * @return 保存返回结果
	 */
	public SaveOddsResultVO saveOdds(OddsVO oddsVO) {
		Odds odds = new Odds();
		BeanUtils.copyProperties(oddsVO, odds);
		this.oddsMapper.insertSelective(odds);
		SaveOddsResultVO resultVO = new SaveOddsResultVO();
		return resultVO;
	}

	public List<OddsVO> queryOddsByMatchNo(String matchNo) {
		List<Odds> list = this.oddsMapper.selectByMatchNo(matchNo);
		
		List<OddsVO> resultList = new ArrayList<OddsVO>();
		
		return resultList;
	}

}
