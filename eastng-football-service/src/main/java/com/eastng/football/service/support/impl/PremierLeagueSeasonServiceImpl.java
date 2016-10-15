package com.eastng.football.service.support.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.football.api.constant.CommonConstant;
import com.eastng.football.api.exception.FootBallBizException;
import com.eastng.football.api.service.crawler.SeasonCrawlerService;
import com.eastng.football.api.service.lottery.OddsService;
import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.SeasonService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.SeasonVo;
import com.eastng.football.api.vo.match.TeamVO;

@Service("premierLeagueSeasonSerice")
public class PremierLeagueSeasonServiceImpl implements SeasonCrawlerService {

    private static final Log log = LogFactory.getLog(PremierLeagueSeasonServiceImpl.class);
    
    @Autowired
    private SeasonService seasonService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private MatchService matchService;
    
    @Autowired
    private OddsService oddsService;

    @Override
    public void crawler(String url, String seasonNo) throws IOException {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(30000).get();
        } catch (IOException e) {
            log.error("获取地址失败" + url, e);
            throw e;
        }

        SeasonVo seasonVo = seasonService.querySeasonBySeasonNo(seasonNo);
        if (seasonVo == null) {
            throw new RuntimeException("获取赛季信息失败" + seasonNo);
        }
        String seasonName = seasonVo.getSeasonName();
        String leagueNo = seasonVo.getLeagueNo();

        // LeagueInfoVO leagueInfoVO =
        // this.leagueInfoService.queryLeagueInfoByLeagueNo(leagueNo);

        Elements elements = doc.select("#team_fight_table tr");
        for (Element element : elements) {
            Elements s = element.select("td");

            // 比赛时间
            String matchTime = s.get(0).text();
            if (matchTime.equals("时间")) {
                continue;
            }

            // 比分
            String score = s.get(3).text();

            // 比赛详情
            MatchVO matchVO = new MatchVO();
            matchVO.setSeasonNo(seasonNo);
            
            String matchTimeSubstr = matchTime.substring(0, 2);
            if (Integer.parseInt(matchTimeSubstr) >= 8) {
                // 页面上没有年份，8月之后的都是前一年，5月前是当年，英超赛季赛程当年8月到下一年5月
                matchTime = seasonName.substring(0, 4) + "-" + matchTime;
            } else {
                matchTime = seasonName.substring(5, 9) + "-" + matchTime;
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            try {
                Date beginDate = sdf1.parse(matchTime);
                matchVO.setMatchTime(beginDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // 主队名称
            String hostName = s.get(2).text();
            matchVO.setHostShortName(hostName);

            TeamVO teamVO1 = new TeamVO();
            teamVO1.setShortName(hostName);
            teamVO1.setTeamType("1");
            teamVO1.setContinent(leagueNo.substring(0, 3) + "000");
            teamVO1.setCountry(leagueNo.substring(0, 6));
            teamVO1.setSeasonNo(seasonNo);
            String teamNo = teamService.saveTeam(teamVO1);
            matchVO.setHostTeamNo(teamNo);

            String r = s.get(1).text();
            try {
                // 轮次
                matchVO.setRound(Integer.parseInt(r));
            } catch (RuntimeException e) {
                continue;
            }
            // 客队名称
            String guestName = s.get(4).text();
            matchVO.setGuestShortName(guestName);

            TeamVO teamVO2 = new TeamVO();
            teamVO2.setShortName(guestName);
            teamVO2.setTeamType("1");
            teamVO2.setContinent(leagueNo.substring(0, 3) + "000");
            teamVO2.setCountry(leagueNo.substring(0, 6));
            teamVO2.setSeasonNo(seasonNo);
            String teamNo1 = teamService.saveTeam(teamVO2);
            matchVO.setGuestTeamNo(teamNo1);

            // 处理比分 页面上格式:2-3,去掉横杠，获取主客队进球
            if (!StringUtils.isEmpty(score)) {
                int matchScore = score.indexOf("-");
                if (matchScore > -1) {
                    String hostGoal = score.substring(0, matchScore);
                    String guestGoal = score.substring(matchScore + 1);
                    if (!StringUtils.isEmpty(hostGoal)) {
                        matchVO.setHostGoal(Integer.parseInt(hostGoal));
                    }
                    if (!StringUtils.isEmpty(guestGoal)) {
                        matchVO.setGuestGoal(Integer.parseInt(guestGoal));
                    }
                    matchVO.setMatchStatus(CommonConstant.MATCH_STATUS_END);
                } else {
                    // 状态
                    matchVO.setMatchStatus(CommonConstant.MATCH_STATUS_NOT_BEGIN);
                }
            }

            // 联赛编号
            matchVO.setLeagueNo(leagueNo);
            // 赛季
            matchVO.setSeasonName(seasonName);
            // 保存比赛信息
            String matchNo = "";
            try {
                log.info(ToStringBuilder.reflectionToString(matchVO, ToStringStyle.MULTI_LINE_STYLE));
                matchNo = matchService.saveMatch(matchVO);
                log.info("保存比赛信息成功：" + matchNo);
            } catch (FootBallBizException e1) {
                log.error("保存比赛信息失败", e1);
            }

            // 比赛完成爬取赔率信息
            if (matchVO.getMatchStatus().equals(CommonConstant.MATCH_STATUS_END)
                    || matchVO.getMatchStatus().equals(CommonConstant.MATCH_STATUS_NOT_BEGIN)) {
                Elements a = element.select("a");
                String odds = a.get(1).attr("href");
                // 未开赛取第一个链接
                if (s.get(3).text().equals("VS")) {
                    odds = a.get(0).attr("href");
                }
                log.info("http://www.okooo.com" + odds + "change/27/");
                try {
                    Document oddsdoc = Jsoup.connect("http://www.okooo.com" + odds + "change/27/").timeout(20000).get();
                    Elements tr = oddsdoc.select("table tr");
                    for (int i = 4; i < tr.size(); i++) {
                        Elements td = tr.get(i).select("td");
                        if (td.size() < 5 || StringUtils.isEmpty(td.get(0).text())) {
                            continue;
                        }
                        OddsVO oddsVO = new OddsVO();
                        oddsVO.setMatchNo(matchNo);
                        oddsVO.setCompany("Bet365");
                        // 距离开赛时间
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
                        // 保存赔率信息
                        oddsService.saveOdds(oddsVO);
                        log.info("保存赔率成功");
                    }
                } catch (IOException e) {
                    log.info("保存赔率成功失败", e);
                    continue;
                }
            }
            try {
                log.info("-----------------------线程等待10秒---------------------------");
                Thread.sleep(20000);
                log.info("-----------------------线程恢复启动---------------------------");
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
