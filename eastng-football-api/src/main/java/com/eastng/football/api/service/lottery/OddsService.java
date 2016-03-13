package com.eastng.football.api.service.lottery;

import java.util.List;

import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.lottery.SaveOddsResultVO;

/**
 * 赔率信息服务接口
 * @author caojia
 */
public interface OddsService {
	
	/**
	 * 保存赔率信息
	 * @param oddsVO
	 * @return 保存返回结果
	 */
	public SaveOddsResultVO saveOdds(OddsVO oddsVO);
	
	/**
	 * 根据比赛编号查询赔率信息列表
	 * @param matchNo 比赛编号
	 * @return 赔率信息列表
	 */
	public List<OddsVO> queryOddsByMatchNo(String matchNo);

}
