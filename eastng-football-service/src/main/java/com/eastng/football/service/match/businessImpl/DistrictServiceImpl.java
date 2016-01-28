package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;

import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.core.entity.match.District;
import com.eastng.football.util.BeanUtils;

public class DistrictServiceImpl implements DistrictService {

	public Integer saveDistrict(DistrictVO districtVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer saveDistricts(List<DistrictVO> districtVOs) {
		List<District> list = new ArrayList<District>();
		BeanUtils.copyProperties(districtVOs, list);
		
		return null;
	}

}
