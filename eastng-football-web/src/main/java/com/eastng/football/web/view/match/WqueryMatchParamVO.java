package com.eastng.football.web.view.match;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.eastng.football.api.vo.common.CommonParamVO;
import com.eastng.football.api.vo.common.PageParam;

/**
 * 查询比赛信息入参VO类
 * @author caojia
 *
 */
public class WqueryMatchParamVO extends PageParam{

	//比赛日期
	private Date matchDate;

	@DateTimeFormat(pattern="yyyyMMdd") 
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	
}
