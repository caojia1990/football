package com.eastng.football.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.common.PageResult;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.lottery.QueryOddsParamVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.web.view.common.ListResponse;
import com.eastng.football.web.view.easyui.DataGridResult;
import com.eastng.football.web.view.lottery.OddsResultVO;

@Controller
public class LotteryController {

    @Autowired
    private OddsService oddsService;
    
    @Autowired
    private MatchService matchService;
    
    @RequestMapping(value="queryOdds" ,method = RequestMethod.POST)
    @ResponseBody
    public DataGridResult<OddsResultVO> queryOddsInfoByMatchNo(@RequestParam(value="matchNo" ,required=false)String matchNo,
            @RequestParam(value="rows",required = false)int rows,
            @RequestParam(value="page",required = false)int page){
        
        QueryOddsParamVO paramVO = new QueryOddsParamVO();
        paramVO.setMatchNo(matchNo);
        paramVO.setPage(page);
        paramVO.setRows(rows);
        PageResult<OddsVO> pageResult = oddsService.queryOddsPageByMatchNo(paramVO);
        
        DataGridResult<OddsResultVO> result= new DataGridResult<OddsResultVO>();
        result.setTotal(pageResult.getTotal());
        
        //封装返回信息
        List<OddsResultVO> list = new ArrayList<OddsResultVO>();
        if(!StringUtils.isEmpty(pageResult)){
            for(OddsVO oddsVO:pageResult.getResult()){
                OddsResultVO oddsResultVO = new OddsResultVO();
                BeanUtils.copyProperties(oddsVO, oddsResultVO);
                list.add(oddsResultVO);
            }
        }
        result.setRows(list);
        return result;
    }
    
    
    /**
     * 查询赔率变化曲线
     * @param matchNo
     * @return
     */
    @RequestMapping("queryOddsChangeByMatchNo")
    @ResponseBody
    public ListResponse<OddsResultVO> queryOddsChangeByMatchNo(String matchNo){
        ListResponse<OddsResultVO> response = new ListResponse<OddsResultVO>();
        
        MatchVO matchVO = this.matchService.queryMatchByMatchNo(matchNo);
        Date matchTime = matchVO.getMatchTime();
        QueryOddsParamVO paramVO = new QueryOddsParamVO();
        paramVO.setMatchNo(matchNo);
        List<OddsVO> list = this.oddsService.queryOdds(paramVO);
        
        List<OddsResultVO> responseBody = new ArrayList<OddsResultVO>();
        if(list != null && list.size() > 0){
            for(OddsVO oddsVO:list){
                OddsResultVO oddsResultVO = new OddsResultVO();
                BeanUtils.copyProperties(oddsVO, oddsResultVO);
                
                Long diff = matchTime.getTime() - oddsVO.getChangeTime().getTime();
                //时间差
                String timeLeftDigit = "-"+(diff/86400) + "D" + ((diff % 86400) / 3600) + "H" + ((diff % 3600) / 60) + "M";
                oddsResultVO.setTimeLeftDigit(timeLeftDigit);
                responseBody.add(oddsResultVO);
            }
        }
        
        response.setResponseBody(responseBody);
        
        return response;
    }
    
        
    
}
