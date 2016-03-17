package com.eastng.football.service.lottery.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.lottery.QueryOddsParamVO;
import com.eastng.football.api.vo.lottery.SaveOddsResultVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.core.entity.lottery.Odds;
import com.eastng.football.core.entity.lottery.OddsExample;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.service.lottery.persistence.OddsMapper;
import com.eastng.football.util.BeanUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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

	public PageResult<OddsVO> queryOddsByMatchNo(QueryOddsParamVO paramVO) {
		OddsExample example = new OddsExample();
		example.createCriteria().andMatchNoEqualTo(paramVO.getMatchNo());
		example.setOrderByClause("change_time desc");
		//分页查询
		PageHelper.startPage(paramVO.getPage(), paramVO.getRows());
		List<Odds> list = this.oddsMapper.selectByExample(example);
		Page<Odds> page = (Page)list;
		
		PageResult<OddsVO> resultList = new PageResult<OddsVO>();
		List<OddsVO> oddsVOList = new ArrayList<OddsVO>();
		for(Odds Odds:list){
			OddsVO oddVO = new OddsVO();
			BeanUtils.copyProperties(Odds, oddVO);
			oddsVOList.add(oddVO);
		}
		resultList.setTotal(page.getTotal());
		resultList.setResult(oddsVOList);
		return resultList;
	}

}
