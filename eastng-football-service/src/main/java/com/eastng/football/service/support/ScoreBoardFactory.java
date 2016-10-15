package com.eastng.football.service.support;

import com.eastng.football.util.SpringContextUtil;

public class ScoreBoardFactory {
    
    //TODO 创建积分榜
    public ScoreBoardService createScoreBoard(String seasonNo){
        
        //TODO 根据联赛编号创建不同的联赛积分榜计算实现
         
        return (ScoreBoardService) SpringContextUtil.getBean("scoreBoardService");
    }

}
