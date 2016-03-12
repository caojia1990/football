package com.eastng.crawler;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.eastng.football.api.vo.lottery.OddsVO;
import com.eastng.football.api.vo.match.MatchVO;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         Boolean b = !FILTERS.matcher(href).matches()
                 && href.startsWith("http://www.okooo.com/soccer/league/17/schedule/12084/1-1");
         return b;
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url);

         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             //String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             Set<WebURL> links = htmlParseData.getOutgoingUrls();

             /*System.out.println("Text length: " + text.length());
             System.out.println("Html length: " + html.length());*/
             //System.out.println("Html: " + html);
             
             Document doc = Jsoup.parse(html);
             Elements elements = doc.select("#team_fight_table tr");
             for(Element element:elements){
            	 Elements s = element.select("td");
            	 if(s.get(3).text().equals("延期")){
            		 continue;
            	 }
        		 MatchVO matchVO = new MatchVO();
        		 matchVO.setHostShortName(s.get(2).text());
        		 String r = s.get(1).text();
        		 try {
        			 matchVO.setRound(Integer.parseInt(r));
				} catch (RuntimeException e) {
					continue;
				}
        		 
        		 matchVO.setGuestShortName(s.get(4).text());
        		 System.out.println(ToStringBuilder.reflectionToString(matchVO, ToStringStyle.SIMPLE_STYLE));
        		 
        		 Elements a = element.select("a");
        		 String odds = a.get(1).attr("href");
        		 //未开赛取第一个链接
        		 if(s.get(3).text().equals("VS")){
        			 odds = a.get(0).attr("href");
        		 }
        		 System.out.println("http://www.okooo.com"+odds);
        		 try {
					Document oddsdoc = Jsoup.connect("http://www.okooo.com"+odds+"change/27/").get();
					Elements tr = oddsdoc.select("table tr");
					for(int i= 2;i<tr.size();i++){
						Elements td = tr.get(i).select("td");
						if(td.size()<5){
							continue;
						}
						OddsVO oddsVO = new OddsVO();
						oddsVO.setCompany("Bet365");
						oddsVO.setWin(new BigDecimal(td.get(2).text().substring(0, 4)));
						oddsVO.setDraw(new BigDecimal(td.get(3).text().substring(0, 4)));
						oddsVO.setLose(new BigDecimal(td.get(4).text().substring(0, 4)));
						String time = td.get(0).text().substring(0, 16);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
						try {
							Date beginDate = sdf.parse(time);
							oddsVO.setChangeTime(beginDate);
							matchVO.setMatchTime(beginDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						 System.out.println(ToStringBuilder.reflectionToString(oddsVO, ToStringStyle.MULTI_LINE_STYLE));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
             }
             
         }
    }
}
