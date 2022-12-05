package com.ssafy.spring.house.model.service;

import java.util.List;

import com.ssafy.spring.house.model.HouseInfo;


public interface HouseInfoService {

	HouseInfo search(int no);

	List<HouseInfo> searchBookMark(String dongCode);
	
	List<HouseInfo> searchEach(int pgno, String sido, String gugun, String dong, String aptName);
	
	List<HouseInfo> searchAll();
}
