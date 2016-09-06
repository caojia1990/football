package com.eastng.football.service.match.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.core.entity.match.District;
import com.eastng.football.core.entity.match.DistrictExample;
import com.eastng.football.core.entity.match.DistrictExample.Criteria;
import com.eastng.football.core.service.match.persistence.DistrictMapper;
import com.eastng.football.util.BeanUtils;

public class DistrictServiceImpl implements DistrictService {

    static Logger logger = Logger.getLogger(DistrictServiceImpl.class);
    
    @Autowired
    private DistrictMapper districtMapper;
    
    public Integer saveDistrict(DistrictVO districtVO) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 批量保存地区信息
     */
    public Integer saveDistricts(List<DistrictVO> districtVOs) {
        List<District> records = new ArrayList<District>();
        for(DistrictVO districtVO:districtVOs){
            District record = new District();
            BeanUtils.copyProperties(districtVO, record);
            records.add(record);
        }
        return districtMapper.batchInsert(records);
    }

    /**
     * 根据地区号查询子地区信息
     */
    public List<DistrictVO> queryDistrictByPid(String pid){
        
        DistrictExample example = new DistrictExample();
        Criteria criteria = example.createCriteria();
        if(StringUtils.isEmpty(pid)){
            criteria.andParentDistrictNoIsNull();
        }else {
            criteria.andParentDistrictNoEqualTo(pid);
        }
        List<District> records = this.districtMapper.selectByExample(example);
        List<DistrictVO> districtVOs = new ArrayList<DistrictVO>();
        for(District record:records){
            DistrictVO districtVO = new DistrictVO();
            BeanUtils.copyProperties(record, districtVO);
            districtVOs.add(districtVO);
        }
        return districtVOs;
    }

    @Override
    public List<DistrictVO> queryCountryByContinentId(String continentId, String hasLeague) throws FootBallBizException {
        
        if(StringUtils.isEmpty(continentId)){
            throw new FootBallBizException("", "洲id为空");
        }
        List<District> districts = null;
        if(!StringUtils.isEmpty(hasLeague)&&hasLeague.equals("1")){
            districts = this.districtMapper.selectCountryWithLeagueByContinentNo(continentId);
        }else {
            DistrictExample example = new DistrictExample();
            Criteria criteria = example.createCriteria();
            criteria.andParentDistrictNoEqualTo(continentId);
            districts = this.districtMapper.selectByExample(example);
        }
        
        
        return null;
    }  
    
}
