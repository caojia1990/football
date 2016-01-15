package com.eastng.football.web.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;

@Controller
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping("queryMatchByMatchNo")
	@ResponseBody
	public MatchVO queryMatchByMatchNo(String matchNo){
		return this.matchService.queryMatchByMatchNo("123");
	}
	
	/**
	 * 根据日期查询比赛信息
	 * @param date 
	 * @return
	 */
	@RequestMapping("queryMatchByDate")
	@ResponseBody
	public List<MatchVO> queryMatchByDate(Date date){
		QueryMatchParamVO paramVO = new QueryMatchParamVO();
		paramVO.setBeginDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		paramVO.setEndDate(cal.getTime());
		return this.matchService.queryMatchSchedule(paramVO);
	}
	
	@RequestMapping("uploadMatchSchedule")
	@ResponseBody
	public String handleFormUpload(MultipartFile file1, HttpServletRequest request) { //请求参数一定要与form中的参数名对应
        System.out.println(file1.getSize());
        if (!file1.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
            System.out.println(path);
            File file = new File(path + new Date().getTime() + ".xlsx"); //新建一个文件
            try {
                FileUtils.copyInputStreamToFile(file1.getInputStream(), file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

}
