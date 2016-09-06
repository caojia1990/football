package com.eastng.football.api.service.match;

import java.util.List;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.vo.match.DistrictVO;

/**
 * 地区信息服务接口
 * @author caojia
 */
public interface DistrictService {
	
	/**
	 * 保存地区信息
	 * @param districtVO
	 * @return
	 */
	public Integer saveDistrict(DistrictVO districtVO);
	
	/**
	 * 批量保存地区信息
	 * @param districtVOs
	 * @return
	 */
	public Integer saveDistricts(List<DistrictVO> districtVOs);
	
	/**
	 * 根据父节点Id查询子节点
	 * @param pid
	 * @return
	 */
	public List<DistrictVO> queryDistrictByPid(String pid);
	
	/**
	 * 根据洲id查询国家列表
	 * @param continentId
	 * @param hasLeague 1:有联赛 0:无限制
	 * @return
	 * @throws FootBallBizException 
	 */
	public List<DistrictVO> queryCountryByContinentId(String continentId ,String hasLeague) throws FootBallBizException;
	
}
