package com.eastng.football.service.match;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.test.BaseJunit4Test;

public class DistrictServiceTest extends BaseJunit4Test{
    
    @Resource
    private DistrictService districtService;
    
    @Test
    public void queryCountryByContinentIdTest(){
        
        try {
            List<DistrictVO> list = this.districtService.queryCountryByContinentId("001000", "1");
            
            System.out.println(JSON.toJSONString(list));
        } catch (FootBallBizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
