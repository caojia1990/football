package com.eastng.football.service.match.base.impl;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.core.entity.match.Match;
import com.eastng.football.core.service.match.persistence.MatchMapper;
import com.eastng.football.service.match.base.MatchBaseService;
import com.eastng.football.service.match.businessImpl.MatchServiceImpl;
import com.eastng.football.util.GenerateCodeUtil;

@Service("matchBaseService")
public class MatchBaseServiceImpl implements MatchBaseService {
    
    static Logger logger = Logger.getLogger(MatchServiceImpl.class);
    
    @Autowired
    private MatchMapper matchMapper;

    @Override
    public String saveOrUpdate(Match match) throws FootBallBizException {
        
        if(StringUtils.isEmpty(match)){
            logger.info("入参为空");
            return null;
        }
        if(StringUtils.isEmpty(match.getHostTeamNo())){
            logger.info("主队编号为空");
            throw new FootBallBizException("", "主队编号为空");
        }
        if(StringUtils.isEmpty(match.getGuestTeamNo())){
            logger.info("客队编号为空");
            throw new FootBallBizException("", "客队编号为空");
        }
        if(StringUtils.isEmpty(match.getLeagueNo())){
            logger.info("联赛编号为空");
            throw new FootBallBizException("", "联赛编号为空");
        }
        if(StringUtils.isEmpty(match.getSeasonName())){
            logger.info("赛季名称为空");
            throw new FootBallBizException("", "赛季名称为空");
        }
        if(StringUtils.isEmpty(match.getRound())){
            logger.info("轮次为空");
            throw new FootBallBizException("", "轮次为空");
        }
        
        Match record = new Match();
        record.setHostTeamNo(match.getHostTeamNo());
        record.setGuestTeamNo(match.getGuestTeamNo());
        record.setLeagueNo(match.getLeagueNo());
        record.setSeasonName(match.getSeasonName());
        record.setRound(match.getRound());
        
        List<Match> list = this.matchMapper.selectByCondition(record);
        
        logger.info("查询比赛信息返回:"+JSON.toJSONString(list));
        
        Match result = new Match();
        String matchNo = null;
        if(list != null && list.size() >0){
            result = list.get(0);
          //若库里存在，则更新
                match.setId(result.getId());
                logger.info("更新比赛信息入参"+ToStringBuilder.reflectionToString(match, ToStringStyle.MULTI_LINE_STYLE));
                this.matchMapper.updateByPrimaryKeySelective(match);
            matchNo = result.getMatchNo();
        }else {
            match.setMatchNo(GenerateCodeUtil.generateMatchNo(""));
            matchNo = match.getMatchNo();
            logger.info("生成比赛编号："+matchNo);
            this.matchMapper.saveMatch(match);
        }
        return matchNo;
    }

}
