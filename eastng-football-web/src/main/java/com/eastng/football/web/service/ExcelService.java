package com.eastng.football.web.service;

import java.io.InputStream;
import java.util.List;

import com.eastng.football.api.vo.match.MatchVO;

public interface ExcelService {
	
	public List<MatchVO> transferMatchFromExcel(InputStream inputStream);
}
