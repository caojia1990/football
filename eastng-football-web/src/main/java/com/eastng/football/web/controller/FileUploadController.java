package com.eastng.football.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.eastng.football.api.service.match.DistrictService;
import com.eastng.football.api.service.match.TeamService;
import com.eastng.football.api.vo.match.DistrictVO;
import com.eastng.football.api.vo.match.TeamVO;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
public class FileUploadController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value="uploadMatchSchedule",method=RequestMethod.POST)
	@ResponseBody
	public String handleFormUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) { //请求参数一定要与form中的参数名对应
        
        try {
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet(0);
			/*for(int i = 0;i<sheet.getRows();i++){
				for(int j = 0;j<sheet.getColumns();j++){
					Cell cell = sheet.getCell(j, i);
					System.out.println(cell.getContents());
				}
			}*/
			
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
	
	@RequestMapping(value="uploadDistrict",method=RequestMethod.POST)
	@ResponseBody
	public String uploadDistrict(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		
		try {
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet(0);
			List<DistrictVO> list = new ArrayList<DistrictVO>();
			for(int i = 0;i<sheet.getRows();i++){
				DistrictVO districtVO = new DistrictVO();
				//地区名称
				districtVO.setDistrictName(sheet.getCell(1, i).getContents());
				//地区编号
				districtVO.setDistrictNo(sheet.getCell(2, i).getContents());
				//上级地区编号
				districtVO.setParentDistrictNo(sheet.getCell(3, i).getContents());
				//地区等级
				districtVO.setDistrictLevel(sheet.getCell(4, i).getContents());
				
				list.add(districtVO);
			}
			districtService.saveDistricts(list);
			
		} catch (BiffException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "sueecss";
	}

}
