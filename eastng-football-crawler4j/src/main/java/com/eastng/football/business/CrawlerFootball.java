package com.eastng.football.business;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.match.MatchVO;

public class CrawlerFootball {
	
	//英超2015/2016赛季
	public void crawler(Document doc,String leagueNo,String seasonName){
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
		MatchService matchService = (MatchService) classPathXmlApplicationContext.getBean("matchService");
		OddsService oddsService = (OddsService) classPathXmlApplicationContext.getBean("oddsService");
		
		Elements elements = doc.select("#team_fight_table tr");
        for(Element element:elements){
       	 Elements s = element.select("td");
       	 
       	//比赛时间
    	String matchTime = s.get(0).text();
    		if(matchTime.equals("时间")){
    			continue;
    		}
       	 
       	 //比分
		String score = s.get(3).text();
		
       	 //比赛详情
   		 MatchVO matchVO = new MatchVO();
   		 
   		 String matchTimeSubstr = matchTime.substring(0, 2);
   		 if(Integer.parseInt(matchTimeSubstr)>=8){
   			 //页面上没有年份，8月之后的都是前一年，5月前是当年，英超赛季赛程当年8月到下一年5月
   			 matchTime = seasonName.substring(0,4)+"-"+matchTime;
   		 }else {
   			 matchTime = seasonName.substring(5,4)+"-"+matchTime;
			}
   		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				try {
					Date beginDate = sdf1.parse(matchTime);
					matchVO.setMatchTime(beginDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
   		 
   		 //主队名称
   		 matchVO.setHostShortName(s.get(2).text());
   		 String r = s.get(1).text();
   		 try {
   			 //轮次
   			 matchVO.setRound(Integer.parseInt(r));
			} catch (RuntimeException e) {
				continue;
			}
   		//客队名称
   		matchVO.setGuestShortName(s.get(4).text());
   		//处理比分   页面上格式:2-3,去掉横杠，获取主客队进球
   		if(!StringUtils.isEmpty(score)){
				int matchScore = score.indexOf("-");
				if(matchScore>-1){
					String hostGoal = score.substring(0, matchScore);
					String guestGoal = score.substring(matchScore+1);
					if(!StringUtils.isEmpty(hostGoal)){
						matchVO.setHostGoal(Integer.parseInt(hostGoal));
					}
					if(!StringUtils.isEmpty(guestGoal)){
						matchVO.setGuestGoal(Integer.parseInt(guestGoal));
					}
					matchVO.setMatchStatus(CommonConstant.MATCH_STATUS_END);
				}else{
					//状态
					matchVO.setMatchStatus(CommonConstant.MATCH_STATUS_NOT_BEGIN);
				}
			}
   		
   		//联赛编号
   		matchVO.setLeagueNo(leagueNo);
   		//赛季
   		matchVO.setSeasonName(seasonName);
   		//保存比赛信息
   		String matchNo = "";
   		try {
   			System.out.println(ToStringBuilder.reflectionToString(matchVO, ToStringStyle.MULTI_LINE_STYLE));
   			matchNo = matchService.saveMatch(matchVO);
			System.out.println("保存比赛信息成功"+matchNo);
		} catch (FootBallBizException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
   		
   		//比赛完成爬取赔率信息
   		if(matchVO.getMatchStatus().equals(CommonConstant.MATCH_STATUS_END)){
	   		Elements a = element.select("a");
	   		String odds = a.get(1).attr("href");
	   		//未开赛取第一个链接
	   		if(s.get(3).text().equals("VS")){
	   			odds = a.get(0).attr("href");
	   		}
	   		System.out.println("http://www.okooo.com"+odds+"change/27/");
				try {
					Document oddsdoc = Jsoup.connect("http://www.okooo.com"+odds+"change/27/").get();
					Elements tr = oddsdoc.select("table tr");
					for(int i= 4;i<tr.size();i++){
						Elements td = tr.get(i).select("td");
						if(td.size()<5||StringUtils.isEmpty(td.get(0).text())){
							continue;
						}
						OddsVO oddsVO = new OddsVO();
						oddsVO.setMatchNo(matchNo);
						oddsVO.setCompany("Bet365");
						//距离开赛时间
						oddsVO.setTimeLeft(td.get(1).text());
						oddsVO.setWin(new BigDecimal(td.get(2).text().substring(0, 4)));
						oddsVO.setDraw(new BigDecimal(td.get(3).text().substring(0, 4)));
						oddsVO.setLose(new BigDecimal(td.get(4).text().substring(0, 4)));
						String time = td.get(0).text().substring(0, 16);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
						try {
							Date beginDate = sdf.parse(time);
							oddsVO.setChangeTime(beginDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						 System.out.println(ToStringBuilder.reflectionToString(oddsVO, ToStringStyle.MULTI_LINE_STYLE));
						 //保存赔率信息
						 oddsService.saveOdds(oddsVO);
						 System.out.println("保存赔率成功");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
	        }
        }
	}

}
