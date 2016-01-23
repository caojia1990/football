package com.eastng.football.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eastng.football.api.service.match.MatchService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.MatchVO;
import com.eastng.football.api.vo.match.QueryMatchParamVO;
import com.eastng.football.api.vo.match.TeamVO;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private TeamService teamService;
	
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
	
	@RequestMapping(value="uploadMatchSchedule",method=RequestMethod.POST)
	@ResponseBody
	public String handleFormUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) { //请求参数一定要与form中的参数名对应
        System.out.println(file.getSize());
        
        try {
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet(0);
			for(int i = 0;i<sheet.getRows();i++){
				for(int j = 0;j<sheet.getColumns();j++){
					Cell cell = sheet.getCell(j, i);
					System.out.println(cell.getContents());
				}
			}
			
			List<TeamVO> list = new ArrayList<TeamVO>();
			sheet = workbook.getSheet(1);
			for(int i = 1;i<sheet.getRows();i++){
				TeamVO teamVO = new TeamVO();
				//球队简称
				teamVO.setShortName(sheet.getCell(0, i).getContents());
				//球队英文名称
				teamVO.setTeamNameEng(sheet.getCell(1, i).getContents());
				/**球队类型  0：国家队；1：俱乐部 */
				teamVO.setTeamType("1");
				list.add(teamVO);
			}
			int record = this.teamService.saveTeams(list);
		} catch (BiffException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
        return "success";
    }
	
	@RequestMapping(value="uploadMatchSchedule2",method=RequestMethod.POST)
	@ResponseBody
	public String uploadMatchSchedual(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：     
        MultipartFile file = multipartRequest.getFile("file");    
        if (!file.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
            System.out.println(path);
            File file1 = new File(path + new Date().getTime() + ".xlsx"); //新建一个文件
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
	}

}
