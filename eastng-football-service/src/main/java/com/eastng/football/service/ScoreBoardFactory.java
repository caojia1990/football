package com.eastng.football.service;

import com.eastng.football.service.support.ScoreBoardService;
import com.eastng.football.service.support.impl.EnglandPremierScoreBoardServiceImpl;
import com.eastng.football.util.SpringContextUtil;

public class ScoreBoardFactory {
    
    //TODO 创建积分榜
    public ScoreBoardService createScoreBoard(String seasonNo){
        
        //TODO 根据联赛编号创建不同的联赛积分榜计算实现
         
        return (ScoreBoardService) SpringContextUtil.getBean("englandPremierScoreBoard");
    }

}
